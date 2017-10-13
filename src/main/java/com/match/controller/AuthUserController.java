package com.match.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.htservice.AuthService;
import com.match.htservice.MenuService;
import com.match.service.DBAuthUserService;
import com.util.DateUtils;
import com.util.EncryptUtil;
import com.util.JUtil;
import com.util.RequestUtil;
import com.util.bean.Result;
import com.util.exception.BusinessException;




@Controller
@RequestMapping("ht/adminuser/")
public class AuthUserController {

	
	
	private final static Logger log = LoggerFactory.getLogger(AuthUserController.class);
	
	@Autowired
	private DBAuthUserService dbAuthUserService;
	@Autowired
	private MenuService menuServcie;
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="userList")  //查询全部角色信息 分页
	public String listrole(HttpServletRequest request){
		try {
			Map<String, Object> param = RequestUtil.getReqMapByPage(request);
			param.put("sysId", "0001");
			//获取总条数
			int totalCount = dbAuthUserService.getUserCount(param);
			RequestUtil.refreshPageParam(request, totalCount);	
			/*List<Map<String, Object>> lists = dbAuthUserService.findByUserPage(param);*/
			
			List<Map<String, Object>> lists = dbAuthUserService.findByUserPage(param);
			for (int i = 0; i < lists.size(); i++) {
				Integer no = (Integer) lists.get(i).get("id");//获取list中ID值
				Map<String, Object> nomap = new HashMap<String,Object>();//new Map
				nomap.put("id", no); //id put 到Map
				List<Map<String, Object>> roles = dbAuthUserService.queryByUser(nomap) ;//根据Map 查询角色
				log.info("获取该ID的所有角色：[{}]",roles);
				lists.get(i).put("roles", roles);		
			}
			log.info("查询参数列表：[{}],其他信息:[{}]",totalCount,param);
			request.setAttribute("lists", lists);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("userName", param.get("userName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ht/adminuser/userList";

	}
	
	@RequestMapping(value="userinsert")
	public String insertRole(){
		return "ht/adminuser/userinsert";
	}
	
	/**
	 * 新增用户信息AJAX
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="addUser",method=RequestMethod.POST)
	public void addRoleUser(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> param = RequestUtil.getReqMap(request);//获取参数
		Map<String, Object> map = new HashMap<String,Object>();
		try {
		   String date = DateUtils.getCurrentDateTime2();
		   String pwd =  EncryptUtil.getEncrypPwd(param.get("userId"), param.get("userPwd"));
		   param.put("userPwd", pwd);
		   param.put("lastLoginTime", date);
		   log.info("新增用户参数[{}]",param);
		   int u = dbAuthUserService.insertAuthUser(param);
		   log.info("新增用户result:[{}]",u);
		   map.put("result", u);
		   RequestUtil.response(response, map);	
		} catch (Exception e) {
		   log.info("用户名存在,或网络错误[{}]");
		   map.put("result", "0");
		}
	}
	
	/**
	 * 新增用户信息
	 * @throws Exception 
	 */
	@RequestMapping(value="user_add",method=RequestMethod.POST)
	public void addRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Result result = new Result();
		try {
			Map<String, Object> user = RequestUtil.getReqMap(request);
			String pwd =   EncryptUtil.getEncrypPwd(user.get("userId"),user.get("userPwd"));
			user.put("userPwd",pwd);
			String date = DateUtils.getCurrentDateTime2();
			user.put("lastLoginTime", date);
			log.info("新增用户参数[{}]",user);
			int u = dbAuthUserService.insertAuthUser(user);	
			if (u==0) {
				throw new Exception("保存失败");
			}
			result.setCode("200");
			result.setMsg("success");
		} catch (Exception   e) {
			result.setCode("202");
			result.setMsg("error");
			e.printStackTrace();
		}
		  RequestUtil.response(response, result);	
	}
	
	/**
	 * 修改用户信息
	 * @param request 获取所有参数
	 * @param out 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="edit/update",method=RequestMethod.POST)
	public void saveEdit(HttpServletRequest request,PrintWriter out,HttpServletResponse response) throws IOException{		
		Map<String, Object> resmap = new HashMap<String,Object>();
		try {
			out = response.getWriter();
			Map<String, Object> user = RequestUtil.getReqMap(request);
			String pwd = EncryptUtil.getEncrypPwd(user.get("userId"),user.get("userPwd"));
			log.info("密码加密:[{}]",pwd);
			user.put("userPwd", pwd);
			log.info("修改用户参数[{}]",user);
			int u = dbAuthUserService.updateAuthUser(user);
			log.info("修改的用户是否成功1:成功,0:失败[{}],修改用户信息[{}]",u,user);
			resmap.put("result", u);
		} catch (Exception e) {
			log.info("用户名已存在");
			resmap.put("result", "-1");
		}  
		out.write(JUtil.toJsonString(resmap));
		out.flush();
		out.close();
		
	}
	
	/**
	 * 获取某用户修改全部的内容
	 * @param request
	 * @return
	 */
	@RequestMapping(value="userEdit")
	public String edit(HttpServletRequest request){	
		try {
			Map<String,Object> users = RequestUtil.getReqMap(request);	

			List<Map<String, Object>> editList = dbAuthUserService.findByUser(users);
			Map<String, Object> user = editList.get(0);
			log.info("修改信息查询[{}],role参数[{}]",editList,user);
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ht/adminuser/userEdit";
	}
	
	
	/**
	 * 用户按钮停用和启用功能
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="disEna")
	@ResponseBody
	public void updateStatus(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> users = RequestUtil.getReqMap(request);		
			Result result = new Result();
			int u = dbAuthUserService.updateAuthUser(users);
			log.info("修改按钮停用：[{}]",u);
			 if(u!=0){	
					result.setCode("200");
					result.setMsg("success");
				 }else{
					result.setCode("200");
					result.setMsg("error");
				  }
			  RequestUtil.response(response, result);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查询全部角色菜单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="RoleMenu")
	public void ajaxTest(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			List<Map<String, Object>> jsonMaps= dbAuthUserService.findRolMenu();
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("jsonMaps", jsonMaps);
			RequestUtil.response(response, jsonMap);
			log.info("ajax信息:[{}]",jsonMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据Id查找用户的角色
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="findUid",method=RequestMethod.POST)
	public void findMenuById(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> param = RequestUtil.getReqMap(request);//获取参数
			List<Map<String,Object>> lists = dbAuthUserService.findUserMenu(param);//获取数据库信息
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("lists", lists);
			RequestUtil.response(response, jsonMap);
			log.info("获取某ID的角色:[{}],参数信息：[{}]",jsonMap,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 跳转角色分配页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="userShri")
	public String findshri(HttpServletRequest request){	
		Map<String,Object> reparam = RequestUtil.getReqMap(request);//获取参数	
		request.setAttribute("uId",reparam.get("uId"));
		return "ht/adminuser/userShri";
	}
	
	/**
	 * 新增角色权限
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="roleAddk",method=RequestMethod.POST)
	@ResponseBody
	public void menufkAddparam(HttpServletRequest request,HttpServletResponse response){
		 try {
			 Map<String,Object> sss = RequestUtil.getReqMap(request);//获取参数
			 Map<String, Object> map = new HashMap<String,Object>();
			 Result result = new Result();
			 String[] ides = sss.get("menuObj").toString().split(",");
			 map.put("privIds", ides);
			 map.put("userId", sss.get("menuRid"));
			 int u = dbAuthUserService.insertUserData(map);
			 log.info("新增角色权限:[{}]",map);
			 if(u!=0){	
				result.setCode("200");
				result.setMsg("success");
			 }else{
				result.setCode("200");
				result.setMsg("error");
			  }
		  RequestUtil.response(response, result);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除某ID所选的全部角色
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="roleAdd",method=RequestMethod.POST)
	@ResponseBody
	public void menuAddparam(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> sss = RequestUtil.getReqMap(request);//获取参数
			Map<String, Object> map = new HashMap<String,Object>();
			Result result = new Result();
			map.put("userId", sss.get("menuRid"));
			int d = dbAuthUserService.deleteUserMenu(map);
			if(d!=0){
				log.info("删除信息返回值[{}],删除参数[{}]",d,map);
				if(sss.get("menuObj")!=null&&""!=sss.get("menuObj")){
					String[] ides = sss.get("menuObj").toString().split(",");
					map.put("privIds", ides);
					int u = dbAuthUserService.insertUserData(map);
					log.info("新增信息返回值[{}],新增参数[{}]",u,map);
					if(u!=0){	
						result.setCode("200");
						result.setMsg("success");
					}else{
						result.setCode("200");
						result.setMsg("error");
					}	
				}else{
					result.setCode("200");
					result.setMsg("success");
				}
			}else{
				
			}
			RequestUtil.response(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 登陆信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="Login")
	public String userLogin(HttpServletRequest request,HttpServletResponse response){
			Map<String, Object> param = RequestUtil.getReqMap(request);
			try {
			   authService.userLogin(param, request);
			   return "redirect:/ht/adminuser/userIndex.do";
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			return "ht/adminuser/userLogin";
	}
	
	@RequestMapping(value="userLogin")
	public String Login(HttpServletRequest request){
		return "ht/adminuser/userLogin";
	}
	
	
	@RequestMapping(value="userCenter")
	public String RePwd(HttpServletRequest request){
		return "ht/adminuser/userCenter";
	}
	
	@RequestMapping(value="updatePwd")
	public void updatePwd(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		Result re = new Result();
		Map<String, Object> param = RequestUtil.getReqMap(request);
		try {
			
			if(authService.updateUserPwd(param, request)){
				re.setCode("200");
				re.setMsg("修改成功");
				session.invalidate();
			}else{
				re.setCode("202");
				re.setMsg("数据有误");
			}
			RequestUtil.response(response, re);
		} catch (Exception e) {
			re.setCode("205");
			re.setMsg("系统异常...");
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="loginOut")
	public void LoginOut(HttpServletRequest request,HttpServletResponse response){
		   HttpSession session = request.getSession();
		   Result result = new Result();
		try {
			 session.invalidate();
			 result.setMsg("success");
			 RequestUtil.response(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 登陆成功后跳转的页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="userIndex")
	public String userIndexs(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();  
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("sysId", session.getAttribute("sysId"));
			param.put("userId", session.getAttribute("id"));
			menuServcie.setMenuToSession(param, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ht/adminuser/userIndex";
	}
	
}
