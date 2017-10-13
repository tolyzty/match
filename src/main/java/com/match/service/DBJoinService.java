package com.match.service;

import java.util.List;
import java.util.Map;

public interface DBJoinService {

	/**
	 * 关联查询-个人加入项目关联查询
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryByJoinAndAcc(Map<String, Object> param);
	
	/**
	 * 查询所有项目列表
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> getJoinList(Map<String,Object> param);
	
	/**
	 * 新增加入
	 * @param param
	 * @return
	 */
	int addJoin(Map<String,Object> param);
	
	/**
	 * 编辑加入信息
	 * @param param
	 * @return
	 */
	int updateJoin(Map<String, Object> param);
	
	/**
	 * 批量编辑操作
	 * @param param
	 * @return
	 */
	int updateItemJoin(Map<String, Object> param);
	
	
	/**
	 * 获取总数
	 * @param param
	 * @return
	 */
	int getJoinCount(Map<String, Object> param);
	
	/**
	 * 调度查询全部加入的信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryByJoinitem(Map<String,Object> param);

	/**
	 * 根据参数查询加入记录
	 */
	
	Map<String, Object> findByJoin(Map<String, Object> param);
	
	/**
	 * 查询最大数据
	 * @param param
	 * @return
	 */
	Map<String, Object> queryByLimit(Map<String, Object> param);
	
	
	/**
	 * 个人中心查询
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> JoinList(Map<String, Object> param);
	
	/**
	 * 修改加入的金额
	 * @param param
	 * @return
	 */
	int updateJoinByAmt(Map<String, Object> param);
	
	/**
	 * 加入表关联项目表查询
	 * @param param
	 * @return
	 */
	Map<String, Object> queryByJoinAndMu(Map<String, Object> param);
	
	/**
	 * 帐户余额小于固定金额,然后批量修改
	 * @param param
	 * @return
	 */
	int updateNotBal(Map<String, Object> param);
	
}
