package com.match.qtservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.util.exception.BusinessException;



public interface JoinService {
	
	
	/**
	 * 新增join
	 * @param param
	 * @param request
	 * @throws BusinessException
	 */
	int addJoin(Map<String, Object> param,HttpServletRequest request) throws BusinessException;
	
	/**
	 * 新增join和account
	 * @param param
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	int insertJoinByAccount(Map<String, Object> param,HttpServletRequest request) throws BusinessException;
	
	/**
	 * 编辑加入信息
	 * @param param
	 * @return
	 */
	int editJoin(Map<String, Object> param,HttpServletRequest request)  throws BusinessException;
	/**
	 * 根据参数查询加入记录
	 */
	
	Map<String, Object> findByJoin(Map<String, Object> param);
	

}
