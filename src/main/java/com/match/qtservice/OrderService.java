package com.match.qtservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.util.exception.BusinessException;

public interface OrderService {
	/**
	 * 已有账户充值 ,新增order和 ACCOUNTiNF
	 * @param request
	 * @param param
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	int insertOrderByAccountInf(HttpServletRequest request,Map<String, Object> param) throws BusinessException, Exception;
	
	/**
	 * 单独充值,交易新增和加入新增
	 * @param request
	 * @param param
	 * @return
	 * @throws BusinessException 
	 * @throws Exception 
	 */
	int insertOrderByJoin(HttpServletRequest request,Map<String, Object> param) throws BusinessException, Exception ;

	/**
	 * 充值成功,修改订单状态,修改帐户余额操作
	 * @param request
	 * @param param
	 * @return
	 */
	int updateOrderByAccount(HttpServletRequest request,Map<String, Object> param) throws BusinessException;
	
	/**
	 * 充值成功,修改订单状态
	 * @param request
	 * @param param
	 * @return
	 * @throws BusinessException
	 */
	int updateOrderByJoin(HttpServletRequest request,Map<String, Object> param) throws BusinessException;

}
