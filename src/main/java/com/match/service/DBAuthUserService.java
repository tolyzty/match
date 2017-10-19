package com.match.service;

import java.util.List;
import java.util.Map;

public interface DBAuthUserService {
	int insertAuthUser(Map<String, Object> param);
	/**
	 * 修改用户信息
	 * @param param
	 * @return
	 */
	int updateAuthUser(Map<String, Object> param);


	Map<String, Object> selectAuthUser(Map<String, Object> param);
	
	/**
	 * 分页查询
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> findByUserPage(Map<String,Object> param);
	
	/**
	 * 关联查询所有用户角色信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryByUser(Map<String, Object> param);

	/**
	 * 查询全部条数
	 * @param param
	 * @return
	 */
	int getUserCount(Map<String, Object> param);
	
	/**
	 * 根据参数查询全部
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> findByUser(Map<String, Object> param);
	
	/**
	 * 查询用户角色菜单
	 * @return
	 */
	List<Map<String,Object>> findUserMenu(Map<String,Object> param);
	

	/**
	 * 查询全部角色菜单
	 * @return
	 */
	List<Map<String, Object>> findRolMenu();
	
	/**
	 * 批量增加权限信息
	 * @param
	 * @return
	 */
	int insertUserData(Map<String,Object> param);
	
	/**
	 * 删除权限信息
	 */
	
	int deleteUserMenu(Map<String,Object> param);
	
	
}
