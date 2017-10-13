package com.match.dao;

import java.util.List;
import java.util.Map;

public interface CustDao {
	
	/**
	 * 查询全部
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> getCustList(Map<String, Object> param);
	
	/**
	 * 获取总数
	 * @param param
	 * @return
	 */
	int getCustCount(Map<String, Object> param);
	
	/**
	 * 新增互助申请
	 * @param param
	 * @return
	 */
	int addCust(Map<String, Object> param);
	/**
	 * 编辑互助申请
	 * @param param
	 * @return
	 */
	int updateCust(Map<String, Object> param);
	
	/**
	 * 根据参数查询cust
	 * @param param
	 * @return
	 */
	Map<String, Object> queryCust(Map<String, Object> param);

}
