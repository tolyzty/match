package com.match.dao;

import java.util.List;
import java.util.Map;

public interface AccountInfDao {
	
	/**
	 * 查询帐户Log信息,余额变动
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> getAccountList(Map<String,Object> param);
	/**
	 * 添加余额变动
	 * @param param
	 * @return
	 */
	int inserAccountInf(Map<String, Object> param);
	
	/**
	 * 根据参数获取某条数据
	 * @param param
	 * @return
	 */
	Map<String, Object> findByQuery(Map<String, Object> param);
	/**
	 * 总数
	 * @param param
	 * @return
	 */
	int findCountInf(Map<String, Object> param);

}
