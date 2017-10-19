package com.match.dao;

import java.util.List;
import java.util.Map;


/**
 * USEDao 接口
 * @author zhangtianyou
 *
 */
public interface BUserDao {
	
	/**
	 * 查询全部用户信息-动态
	 * @param param
	 * @return List
	 */
	List<Map<String,Object>> getUserList(Map<String,Object> param);
	

	/**
	 * 注册用户-执行操作
	 */
	 int registUser(Map<String, Object> param);

	 /**
	  * 返回Map,查询用户表信息-动态
	  * @param param
	  * @return Map
	  */
	 Map<String, Object> queryUserByAll(Map<String, Object> param);
	 
	 /**
	  * 编辑操作
	  * @param prarm
	  * @return
	  */
	 int updateUser(Map<String, Object> prarm);
	 
	 /**
	  * 总条数
	  * @param param
	  * @return
	  */
	 int getUserListCount(Map<String, Object> param);

	/**
	 * 删除用户信息
	 * @param param
	 * @return
	 */
	 int deleteUser(Map<String,Object> param);
}
