package com.match.htservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.htservice.MenuService;
import com.match.service.DBMenuService;
import com.util.bean.MenuTree;



@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private DBMenuService dbMenuServcie;
	
	@Override
	public void setMenuToSession(Map param, HttpSession session) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> menuList = dbMenuServcie.getMenuList(param);
		System.out.println(menuList.size());
		List<MenuTree> menuTree = new ArrayList<MenuTree>();
		for (int i = 0; i < menuList.size(); i++) {
			Map<String, Object> menu = menuList.get(i);
			System.out.println(menu);
			if (menu.get("menuParId")==null || menu.get("menuParId").equals("")) {
				MenuTree tree = new MenuTree();
				tree.setAuthUrl((String)menu.get("authUrl"));
				tree.setMenuCode((String)menu.get("menuCode"));
				tree.setMenuId((String)menu.get("menuId"));
				tree.setMenuName((String)menu.get("menuName"));
				tree.setMenuParId((String)menu.get("menuParId"));
				tree.setMenuStatus((Integer)menu.get("menuStatus"));
				tree.setMenuType((Integer)menu.get("menuType"));
				tree.setMenuUrl((String)menu.get("menuUrl"));
				tree.setSysId((String)menu.get("sysId"));
				setMenuTree(menuList,tree);
				menuTree.add(tree);
			}
		}
		System.out.println("菜单数量"+menuTree.size());
		session.setAttribute("menuList", menuTree);
	}

	/**
	 * 从集合中查询已知菜单的所有等级的子菜单，直到最后一级菜单
	 * @param menuList 集合
	 * @param menu	菜单
	 */
	public void setMenuTree(List<Map<String, Object>> menuList,MenuTree menu) {
		List<MenuTree> childrens = new ArrayList<MenuTree>();
		for (int i = 0; i < menuList.size(); i++) {
			Map<String, Object> map = menuList.get(i);
			if (map.get("menuParId")==null) {
				continue;
			}
			if (map.get("menuParId").equals(menu.getMenuId())) {
				MenuTree children = new MenuTree();
				children.setAuthUrl((String)map.get("authUrl"));
				children.setMenuCode((String)map.get("menuCode"));
				children.setMenuId((String)map.get("menuId"));
				children.setMenuName((String)map.get("menuName"));
				children.setMenuParId((String)map.get("menuParId"));
				children.setMenuStatus((Integer)map.get("menuStatus"));
				children.setMenuType((Integer)map.get("menuType"));
				children.setMenuUrl((String)map.get("menuUrl"));
				children.setSysId((String)map.get("sysId"));
				setMenuTree(menuList,children);
				childrens.add(children);
			}
		}
		menu.setChildren(childrens);
	}

	@Override
	public void insertMenu(Map<String, Object> param) {
		// TODO Auto-generated method stub
		dbMenuServcie.insertMenu(param);
	}
}
