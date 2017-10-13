package com.match.dao;

import java.util.List;
import java.util.Map;

public interface AccountDao {
	
	/**
	 * 查询帐户信息
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> getAccountLists(Map<String,Object> param);
	
	/**
	 * 获取list的总条数
	 * @param param
	 * @return
	 */
	int findCount(Map<String, Object> param);
	/**
	 * 添加帐户信息
	 * @param param
	 * @return
	 */
	int inserAccount(Map<String, Object> param);
	
	/**
	 * 根据参数获取某条数据
	 * @param param
	 * @return
	 */
	Map<String, Object> findByQueryAmt(Map<String, Object> param);
	/**
	 * 编辑帐户信息
	 * @param param
	 * @return
	 */
	int updateAccount(Map<String, Object> param);
	
	/**
	 * 根据ID编辑帐户表
	 * @param param
	 * @return
	 */
	int updateAcc(Map<String, Object> param);
	
	/**
	 * 充值成功后，修改account和accountinf状态
	 * @param param
	 * @return
	 */
	int updateAccByAccInf(Map<String, Object> param);
	
	/**
	 * 批量编辑扣款操作Join
	 * @param param
	 * @return
	 */
	int updateQueyAmt(Map<String, Object> param);

}
