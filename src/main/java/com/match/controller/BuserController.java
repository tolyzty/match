package com.match.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.match.service.DBBUserService;
import com.util.DateUtils;
import com.util.EncryptUtil;
import com.util.MapUtils;
import com.util.RequestUtil;
import com.util.bean.Result;
import com.util.exception.BusinessException;


@Controller
@RequestMapping("/ht/user/")
public class BuserController {

	private final static Logger log = LoggerFactory.getLogger(BuserController.class);
	/**
	 * 跳转页面到-[用户信息页面]
	 */
	private final static String USERLIST = "ht/user/userList";
	/**
	 * 登陆成功跳转页面
	 */
	private final static String USERCENTER = "ht/user/userCenter";
	/**
	 * 跳转页面到-[ 用户注册页面 ]
	 */
	private final static String USERINSERT = "ht/user/userInsert";
	/**
	 * 跳转页面到 - [ 用户登陆页面 ]
	 *
	 */
	private final static String USERLOGIN = "ht/user/userLogin";
	
	/**
	 * 跳转到- [用户编辑]
	 */
	private final static String USEREDIT = "ht/user/userEdit";
	@Autowired
	private DBBUserService dbbUserService;
	
	
	/**
	 * 跳转登陆页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="userLogin")
	public String Login(HttpServletRequest request){
		return USERLOGIN;
	}
	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping(value="userInsert")
	public String registHref(){		
		return USERINSERT;
	}
	/**
	 * 编辑用户跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value="userEdit")
	public String userEdit(HttpServletRequest request){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("用户跳转编辑参数：[{}]",param);
		try {
			Map<String, Object> reMap = dbbUserService.queryUserByAll(param);
			log.info("根据传入参数查询某个用户:[{}]",reMap);
			request.setAttribute("reMap", reMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return USEREDIT;
	}
	
	
	
	
	
	@RequestMapping(value="userList")
	public String getUserList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMapByPage(request);
		log.info("获得用户参数:[{}]",param);
		try {
			List<Map<String, Object>> lists = dbbUserService.getUserList(param);
			int tolalCount = dbbUserService.getUserListCount(param);
			log.info("获取所有用户列表：[{}]",lists);
			RequestUtil.refreshPageParam(request, tolalCount);
			log.info("总条数:[{}]",tolalCount);
			request.setAttribute("lists", lists);
			request.setAttribute("totalCount", tolalCount);
		} catch (Exception e) {
			log.warn("查询错误系统异常：[{}]",e);
		}
		return USERLIST;
	}
	@RequestMapping(value="userCenter")
	public String userCenter(HttpServletRequest request){
		return USERCENTER;
	}
	

	
	
	/**
	 * 执行登陆操作
	 * @param request
	 * @param response
	 * @return 个人中心/登陆页面
	 * @throws BusinessException 数据异常
	 * @throws UnknownHostException 获取Ip异常
	 */
	@RequestMapping(value="userLogins",method=RequestMethod.POST)
	public String userLogin(HttpServletRequest request,HttpServletResponse response) throws BusinessException, UnknownHostException{
		HttpSession session = request.getSession();
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取前台得参数:[{}]",param);
		String pwd = EncryptUtil.getEncrypPwd(param.get("userName"),param.get("userPassword"));
		param.put("userName", param.get("userName"));
		log.info("密码加密[{}]",pwd);
		param.put("userPassword", pwd);
		try {
			Map<String, Object> userMap = dbbUserService.queryUserByAll(param);
			log.info("根据用户名和密码查询:[{}]",userMap);
			if (userMap!=null) {
				 session.setAttribute("userName", userMap.get("userName"));
				 session.setAttribute("id", userMap.get("id"));
				 session.setAttribute("userPhone", userMap.get("userPhone"));
				 InetAddress addr = InetAddress.getLocalHost();
				 String ip=addr.getHostAddress();//获得本机IP
				 String date = DateUtils.getCurrentDateTime2();//获取当前时间
				 log.info("[用户登陆]获取得本地IP:[{}],获取登陆时间:[{}]",ip,date);	
				 return USERCENTER;
			}else{
				log.info("登陆信息:[{}]",userMap);
				request.setAttribute("error", "用户名或密码错误");
				throw new BusinessException("对不起,用户不存在");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return USERLOGIN;
	}
	
	
	/**
	 * 插入用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="insertUser",method=RequestMethod.POST)
	public void registUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> param = RequestUtil.getReqMap(request);
		Result result = new Result();
		log.info("注册信息参数:[{}]",param);

		try {
			String userName = param.get("userName").toString().trim();
			String userPassword = param.get("pwd").toString().trim();
			String pwd = EncryptUtil.getEncrypPwd(userPassword+"",userPassword+"");
			log.info("注册用户加密后密码:[{}]",pwd);
			MapUtils.doing(param, "userName","userPhone","pwd");
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userName", userName);
			userMap.put("userPhone", param.get("userPhone") );
			userMap.put("userPassword",pwd);
			userMap.put("userEmail", param.get("userEmail"));
			userMap.put("createTime",DateUtils.getCurrentDateTime2() );
			log.info("注册用户信息:[{}]",userMap);
			int u = dbbUserService.registUser(userMap);	
			log.info("注册信息返回值:[{}]",u);
			if (u==1) {
			  log.debug("注册成功");
			  result.setCode("200");
			  result.setMsg("success");
			}else{
			  result.setCode("201");
			  result.setMsg("error");
			}
			
		} catch (Exception e) {
			result.setCode("202");
			result.setMsg("errorException");
		}
		RequestUtil.response(response, result);
	}
	
	/**
	 * 执行用户编辑操作
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping(value="updateUser",method=RequestMethod.POST)
	public void updateUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> param = RequestUtil.getReqMap(request);
		Result result = new Result();
		log.info("编辑用户所有参数:[{}]",param);
		try {
			int u = dbbUserService.updateUser(param);
			if (u==1) {
			  log.info("编辑成功");
			  result.setCode("200");
			  result.setMsg("success");
			}else{
			  result.setCode("201");
			  result.setMsg("error");	
			}
		} catch (Exception e) {
			result.setCode("202");
			result.setMsg("errorException");
			log.warn("执行编辑系统异常:[{}]",e);
		}
		RequestUtil.response(response, result);
	}
	
	/**
	 * 用户按钮停用和启用功能
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="disEnas")
	public void updateStatus(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Result result = new Result();
		try {
			Map<String,Object> dis = RequestUtil.getReqMap(request);		
			log.info("获得是否禁用的参数:[{}]",dis);
			int u = dbbUserService.updateUser(dis);
			log.info("修改按钮停用：[{}]",u);
			 if(u!=0){	
					result.setCode("200");
					result.setMsg("success");
				 }else{
					result.setCode("201");
					result.setMsg("error");
				  }
		} catch (Exception e) {
			 result.setCode("202");
			 result.setMsg("errorException");
			e.printStackTrace();
		}
		RequestUtil.response(response, result);	
	}
	
}
