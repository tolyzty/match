package com.match.dao;

import java.util.List;
import java.util.Map;

public interface MenuDao {
	/**
	 * 查询用户菜单
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getMenuList(Map<String, Object> param);
	
	/**
	 * 查询所有菜单
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getAllMenuList(Map<String, Object> param);
	
	/**
	 * 根据编号查询菜单
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> getMenuByID(Map<String, Object> param);
	
	/**
	 * 修改菜单信息
	 * @param param
	 */
	void updateMenu(Map<String, Object> param);
	
	/**
	 * 增加菜单信息
	 * @param param
	 */
	void insertMenu(Map<String, Object> param);
	
	/**
	 * 根据菜单获取子菜单最大编号，用于控制新增菜单的编号
	 * @param param
	 * @return
	 */
	Integer queryMaxId(Map<String, Object> param);
}
