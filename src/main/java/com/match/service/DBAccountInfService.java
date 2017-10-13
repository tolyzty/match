package com.match.service;

import java.util.List;
import java.util.Map;

public interface DBAccountInfService {
	
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
	 * 添加账户变更历史信息
	 * @param param 
	 * @return
	 * @throws Exception
	 */
	public int addAccountHis(Map<String , Object> param) throws Exception;
	
	/**
	 * 编辑帐户余额信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int modifyAccountAmt(Map<String, Object> param)throws Exception;
	
	/**
	 * 新增帐户信息
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public int addAccountAmt(Map<String, Object> param) throws Exception;
	
	/**
	 * 总数量
	 * @param param
	 * @return
	 */
	public int findCountInf(Map<String, Object> param);
}
