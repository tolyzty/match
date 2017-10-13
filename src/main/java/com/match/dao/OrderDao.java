package com.match.dao;

import java.util.List;
import java.util.Map;


/**
 * 互助项目接口
 * @author 13376
 *
 */
public interface OrderDao {
	
	/**
	 * 查询所有加入列表
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> getOrderList(Map<String,Object> param);
	/**
	 * 新增交易
	 * @param param
	 * @return
	 */
	int inserOrder(Map<String, Object> param);
	
	/**
	 * Map根据条件查询订单
	 * @param param
	 * @return
	 */
	Map<String, Object> findByOrder(Map<String, Object> param);
	
	/**
	 * 根据条件查询某一条记录
	 * @param param
	 * @return
	 */
	Map<String, Object> orderByLimit(Map<String, Object> param);
	
	/**
	 * 编辑交易信息
	 * @param param
	 * @return
	 */
	int updateOrder(Map<String, Object> param);
	
	/**
	 * 修改order和join状态
	 * @param param
	 * @return
	 */
	int updateJoinByOrder(Map<String, Object> param);
	/**
	 * 根据关联查询order和Join
	 * @param param
	 * @return
	 */
	Map<String, Object> queryByOrderAndJoin(Map<String, Object> param);
}
