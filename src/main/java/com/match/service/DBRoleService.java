package com.match.service;

import java.util.List;
import java.util.Map;


public interface DBRoleService {

	
	/**
	 * 查询全部权限信息
	 * @param roles
	 * @return
	 */
	List<Map<String, Object>> findAll();
	

	/**
	 * 新增角色信息操作
	 * @param role
	 * @return
	 */
	int insertRole(Map<String, Object> role);
	/**
	 * 修改角色信息操作
	 * @param role
	 * @return
	 */
	int updateById(Map<String, Object> role);
	/**
	 * 根据ID查询角色信息
	 * @param role
	 * @return
	 */
	List<Map<String, Object>> findById(Map<String, Object> role);
	/**
	 * 删除角色,根据ID查询联合查询
	 * @param role
	 * @return
	 */
	List<Map<String,Object>> findByKayId(Map<String,Object> role);
	
	/**
	 * 删除角色操作
	 * @param role
	 * @return int
	 */
	int deletes(Map<String,Object> role);
	
	/**
	 * 分页查询
	 * @param role
	 * @return
	 */
	List<Map<String,Object>> findByPage(Map<String, Object> role);
	
	/**
	 * 获取总数
	 * @param role
	 * @return
	 */
	int findByCount(Map<String,Object> role);
	
	/**
	 * 查询全部权限菜单
	 * @return
	 */
	List<Map<String,Object>> findRoleMenu(Map<String,Object> role);
	
	List<Map<String, Object>> findMenu();
	
	/**
	 * 批量增加
	 * @param rolemenu
	 * @return
	 */
	int insertCheckData(Map<String,Object> rolemenu);
	
	/**
	 * 删除权限信息
	 */
	
	int deleteMenu(Map<String,Object> role);
	

}
