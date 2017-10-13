package com.match.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.service.DBRoleService;
import com.util.JUtil;
import com.util.RequestUtil;
import com.util.bean.Result;


@Controller
@RequestMapping("ht/role/")
public class RoleController {
	
	private final static Logger log = LoggerFactory.getLogger(RoleController.class);
	
	
	@Autowired
	private DBRoleService dbRoleService;
	
	
	@RequestMapping(value="roleinsert")
	public String insertRole(){
		return "ht/role/roleinsert";
	}
	
	@RequestMapping(value="roleShri")
	public String findshri(HttpServletRequest request){	
		Map<String,Object> reparam = RequestUtil.getReqMap(request);//获取参数	
		request.setAttribute("roleId",reparam.get("roleId"));
		return "ht/role/roleShri";
	}
	
	@RequestMapping(value="menuAddk",method=RequestMethod.POST)
	@ResponseBody
	public void menufkAddparam(HttpServletRequest request,HttpServletResponse response){
		 try {
			 Map<String,Object> sss = RequestUtil.getReqMap(request);//获取参数
			 Map<String, Object> map = new HashMap<String,Object>();
			 Result result = new Result();
			 String[] ides = sss.get("menuObj").toString().split(",");
			 map.put("privIds", ides);
			 map.put("userId", sss.get("menuRid"));
			 int u = dbRoleService.insertCheckData(map);
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
	
	@RequestMapping(value="menuAdd",method=RequestMethod.POST)
	@ResponseBody
	public void menuAddparam(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> sss = RequestUtil.getReqMap(request);//获取参数
			Map<String, Object> map = new HashMap<String,Object>();
			Result result = new Result();
			map.put("userId", sss.get("menuRid"));
			int d = dbRoleService.deleteMenu(map);
			if(d!=0){
				log.debug("删除信息返回值[{}],删除参数[{}]",d,map);
				if(sss.get("menuObj")!=null&&""!=sss.get("menuObj")){
					String[] ides = sss.get("menuObj").toString().split(",");
					map.put("privIds", ides);
					int u = dbRoleService.insertCheckData(map);
					log.debug("新增信息返回值[{}],新增参数[{}]",u,map);
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
	
	
	
	
	@RequestMapping(value="ajaxMenu")
	public void ajaxTest(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			List<Map<String, Object>> jsonMaps= dbRoleService.findMenu();
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("jsonMaps", jsonMaps);
			RequestUtil.response(response, jsonMap);
			log.debug("ajax ajax....[{}]",jsonMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="findBid",method=RequestMethod.POST)
	public void findMenuById(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,Object> role = RequestUtil.getReqMap(request);//获取参数
			List<Map<String,Object>> lists = dbRoleService.findRoleMenu(role);//获取数据库信息
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			jsonMap.put("lists", lists);
			RequestUtil.response(response, jsonMap);
			log.debug("获取某ID的权限:[{}],参数信息：[{}]",jsonMap,role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@RequestMapping(value="role_add",method=RequestMethod.POST)
	public void addRole(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Result result = new Result();
		try {
			Map<String, Object> role = RequestUtil.getReqMap(request);
			log.debug("新增的角色参数[{}]",role);
			int u = dbRoleService.insertRole(role);
			if (u==0) {
				throw new Exception();
			}
			result.setCode("200");
			result.setMsg("success");
		} catch (Exception e) {
			result.setCode("202");
			result.setMsg("error");
			e.printStackTrace();
		} 
		RequestUtil.response(response, result); 
	
	}
	
	@RequestMapping(value="roleEdit")
	public String edit(Model model,HttpServletRequest request){	
		try {
			Map<String,Object> roles = RequestUtil.getReqMap(request);
			List<Map<String, Object>> editList = dbRoleService.findById(roles);
			Map<String, Object> role = editList.get(0);
			log.debug("修改信息查询[{}],role参数[{}]",editList,role);
			request.setAttribute("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ht/role/roleEdit";
	}
	
	
	@RequestMapping(value="edit/update",method=RequestMethod.POST)
	public void saveEdit(HttpServletRequest request,HttpServletResponse response) throws Exception{		
		Result result = new Result();
		try {
			
			Map<String, Object> role = RequestUtil.getReqMap(request);
			int u = dbRoleService.updateById(role);
			Map<String, Object> resmap = new HashMap<String,Object>();
			resmap.put("result", u);
			result.setCode("200");
			result.setMsg("success");

			//out.write(JUtil.toJsonString(resmap));
			log.debug("修改的角色是否成功1:成功,0:失败[{}],修改角色信息[{}]",u,role);
		} catch (Exception e) {
			result.setCode("202");
			result.setMsg("error");
			e.printStackTrace();
		} 
			RequestUtil.response(response, result); 
		
	}
	@RequestMapping(value="findByKeyId")
	public void findByKeyId(HttpServletRequest request,PrintWriter out,HttpServletResponse response){
		int role = 1;
		try {
		 String roleId = request.getParameter("roleId");
		 Map<String, Object> roles = new HashMap<String,Object>();
		 roles.put("roleId", roleId);
		 List<Map<String, Object>> findList = dbRoleService.findByKayId(roles);
		 if (findList.isEmpty()) {
			role = 0;
		}
		 Map<String, Object> resmap = new HashMap<String,Object>();
		 resmap.put("result", role);
		 log.debug("修改信息查询[{}],role参数[{}]",findList,role);
		 out.write(JUtil.toJsonString(resmap));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value="deletes")
	public void deletes(HttpServletRequest request,PrintWriter out,HttpServletResponse response){
		String roleId = request.getParameter("roleId");
		try {
			out = response.getWriter();
			Map<String, Object> roles = new HashMap<String,Object>();
			roles.put("roleId", roleId);
			int u = dbRoleService.deletes(roles);
			Map<String, Object> resmap = new HashMap<String,Object>();
			resmap.put("result", u);
			out.write(JUtil.toJsonString(resmap));
			log.debug("删除角色操作[{}],返回值[{}]",roles,u);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}
	
	
	@RequestMapping(value="roleList")  //查询全部角色信息 分页
	public String listrole(HttpServletRequest request){
		try {
			Map<String, Object> param = RequestUtil.getReqMapByPage(request);
			//获取总条数
			int totalCount = dbRoleService.findByCount(param);
			RequestUtil.refreshPageParam(request, totalCount);
			List<Map<String, Object>> lists = dbRoleService.findByPage(param);	
			log.debug("查询参数列表：[{}],其他信息:[{}]",totalCount,param);
			request.setAttribute("lists", lists);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("roleName", param.get("roleName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ht/role/roleList";

	}
    
	
	
}
