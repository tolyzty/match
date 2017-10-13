package com.match.qtcontroller;

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

import com.match.controller.AccountController;
import com.match.qtservice.UserService;
import com.match.service.DBBUserService;
import com.match.service.DBJoinService;
import com.util.DateUtils;
import com.util.EncryptUtil;
import com.util.MapUtils;
import com.util.RequestUtil;
import com.util.constants.Constants;
import com.util.exception.BusinessException;


@Controller
@RequestMapping("/user/")
public class UserController {
	
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DBBUserService  dbbUserService;
	@Autowired
	private	DBJoinService dbJoinService;
	
	/**
	 * 跳转到登录页面
	 */
	private final static String LOGIN = "user/login";
	
	/**
	 * 个人中心页面
	 * @param request
	 * @return
	 */
	private final static String CONTENT = "user/content";
	
	/**
	 * 注册页面
	 */
	private final static String REGISTER = "user/register";
	
	private final static String USERJOIN = "user/userjoin";
	
	/**
	 * 跳转登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login")
	public String login(HttpServletRequest request){
		return LOGIN;
	}
	
	/**
	 * 跳转到会员中心
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="content")
	public String Content(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		if (session.getAttribute("consumerNo")==null) {
			return LOGIN;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		request.setAttribute("userPhone", session.getAttribute("userPhone"));
		paramMap.put("userPhone", session.getAttribute("userPhone"));
		Map<String, Object> rsMap = dbbUserService.queryUserByAll(paramMap);
		log.info("根据session手机号获取:[{}]",rsMap);
		request.setAttribute("rsmap", rsMap);
		return CONTENT;
	}
	
	@RequestMapping(value="userJoin")
	public String userJoin(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String consumerNo = session.getAttribute("consumerNo")+"";
		log.info("个人中心用户：[{}]",consumerNo);
		Map<String, Object> seMap = new HashMap<String, Object>();
		seMap.put("consumerNo", consumerNo);
		List<Map<String, Object>> lists = dbJoinService.queryByJoinAndAcc(seMap);
		log.info("获取个人中心的List:[{}],总数量:[{}]",lists,lists.size());		
		request.setAttribute("lists", lists);
		return USERJOIN;
	}
	
	/**
	 * 跳转注册页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="register")
	public String Register(HttpServletRequest request){
		String islockId = request.getParameter("islockId");
		log.info("推荐人ID：[{}]",islockId);
		if (islockId==null) {
			islockId="0";
		}
		request.setAttribute("islockId", islockId);
		return REGISTER;
	}
	
	
	/**
	 * 执行登录操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="logins",method=RequestMethod.POST)
	public String uplogin(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取登录参数:[{}]",param);
		try {
			userService.userLogin(param, request);
			return CONTENT;
		} catch (BusinessException e) {
			log.info("登录异常");
			e.printStackTrace();
		}
		return LOGIN;
	}
	
	@RequestMapping(value="registers",method=RequestMethod.POST)
	public String upregist(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		int islockNumber = 0;
		param.put("islock", Constants.ISLOCK_STATUS_NULL);
		log.info("获取注册参数:[{}]",param);
		Map<String,Object> selMap = new HashMap<String, Object>();
		if (!("0").equals(param.get("islockId"))) {
			selMap.put("islockId", param.get("islockId"));
			selMap.put("islock", Constants.ISLOCK_STATUS_SU);
			log.info("根据手机号参数:[{}]",selMap);
			Map<String, Object> smap = dbbUserService.queryUserByAll(selMap);
			log.info("查询推荐人信息:[{}]",smap);
			if (smap!=null) {
				selMap.clear();
				selMap.put("userId", smap.get("userId"));
				islockNumber=Integer.parseInt(smap.get("islockNumber").toString())+1;
				selMap.put("islockNumber", islockNumber);
				log.info("加入:[{}]",selMap);	
				param.put("islock", Constants.ISLOCK_STATUS_BTJ);
			}
			
		}
		try {
			String userPhone = param.get("userPhone").toString().trim();
			MapUtils.doing(param, "userPassword","userPhone");
			param.put("userPhone", userPhone);
			param.put("userPassword", EncryptUtil.getEncrypPwd(param.get("userPhone")+"", param.get("userPassword")+""));
			param.put("createTime", DateUtils.getCurrentDateTime2());
			param.put("islockNumber", "0");
			param.put("islockAmt", "0");
			param.put("awardFlag", "0");
			param.put("awardAmt", "0");
			log.debug("用户注册参数:[{}]",param);
			int u = dbbUserService.registUser(param);
			if (u==1) {
				 log.info("注册成功");
				 if (selMap.size()!=0) {
					 log.info("执行推荐人数：[{}]",selMap);
					 u = dbbUserService.updateUser(selMap);
					 if (u==0) {
						throw new Exception();
					}
				}
				request.setAttribute("success", "注册成功，请登录");
				return LOGIN;
			}
		} catch (Exception e) {
			log.info("注册异常:[{}]",e);
			request.setAttribute("error", "登陆名称已存在，请更换登陆名称");
			e.printStackTrace();
		}
		return REGISTER;
		
	}
	
	@RequestMapping(value="logout")
	public void lougout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
	

}