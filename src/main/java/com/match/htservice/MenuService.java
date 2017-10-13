package com.match.htservice;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface MenuService {
	void setMenuToSession(Map param, HttpSession session);
	/**
	 * 增加菜单信息
	 * @param param
	 */
	void insertMenu(Map<String, Object> param);
}
