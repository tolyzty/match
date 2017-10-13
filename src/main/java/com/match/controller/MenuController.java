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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.htservice.MenuService;
import com.match.service.DBMenuService;
import com.util.JUtil;
import com.util.RequestUtil;



//import com.yafu.service.TestService;


@Controller
@RequestMapping("ht/menu/")
public class MenuController {
	
	private final static Logger log = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuServcie;
	
	@Autowired
	private DBMenuService dbMenuServcie;
	
	@RequestMapping(value="load")
	public String loadMenu(HttpSession session) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sysId", "0001");
		param.put("userId", "1");
		menuServcie.setMenuToSession(param, session);
		return "menu/menuList";
	}
	
	@RequestMapping(value="menuList")
	public String menuList(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sysId", "0001");
		param.put("userId", "1");
		List<Map<String, Object>> menuList = dbMenuServcie.getAllMenuList(param);
		request.setAttribute("menuList", menuList);
		log.debug("查询菜单列表");
		return "menu/menuList";
	}
	
	@RequestMapping(value="edit")
	public String menuEdit(HttpServletRequest request) {
		String menuId = request.getParameter("menuId");
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("menuId", menuId);
		List<Map<String, Object>> menus = dbMenuServcie.getMenuByID(param);
		Map<String, Object> menu = menus.get(0);
		request.setAttribute("menu", menu);
		return "menu/menuEdit";
	}
	
	
	@RequestMapping(value="edit/update")
	public void menuEditUpdate(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			Map<String, Object> param = RequestUtil.getReqMap(request);
			log.debug("修改的菜单参数[{}]",param);
			dbMenuServcie.updateMenu(param);
			Map<String,Object> resMap = new HashMap<String,Object>();
			resMap.put("result", "1");
			out.write(JUtil.toJsonString(resMap));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "menu/menuEdit";
	}
	
	@RequestMapping(value = "insertJsp")
	public String menuInserJsp(HttpServletRequest request) {
		String menuParId = request.getParameter("menuParId");
		HashMap<String, Object> param = new HashMap<String,Object>();
		param.put("menuParId", menuParId);
		Integer menuId = dbMenuServcie.queryMaxId(param);
		if (menuId==null) {
			if (menuParId==null || menuParId.equals("")) {
				request.setAttribute("menuId", "100");
			}else {
				request.setAttribute("menuId", menuParId+"100");
			}
		}else{
			request.setAttribute("menuId", menuId+1);
		}
		request.setAttribute("menuParId", menuParId);
		return "menu/menuInsert";
	}
	
	@RequestMapping(value = "insert")
	public void menuInser(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			Map<String, Object> param = RequestUtil.getReqMap(request);
			log.debug("新增菜单参数[{}]",param);
			menuServcie.insertMenu(param);
			Map<String,Object> resMap = new HashMap<String,Object>();
			resMap.put("result", "1");
			out.write(JUtil.toJsonString(resMap));
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
	}
	
}
