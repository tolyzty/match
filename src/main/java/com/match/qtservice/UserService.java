package com.match.qtservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.util.exception.BusinessException;



public interface UserService {
	

	/**
	 * 用户登陆
	 * @param param
	 * @return
	 * @throws BusinessException 
	 */
	void userLogin(Map<String, Object> param,HttpServletRequest request) throws BusinessException;
	/**
	 * 用户注册
	 * @param param
	 * @param request
	 * @throws BusinessException
	 */
	void register(Map<String, Object> param,HttpServletRequest request) throws BusinessException;
	
	/**
	 * 查询用户,修改推荐人的奖励标示和金额
	 * @param param
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	void queryAndUpdateUser(Map<String, Object> param,HttpServletRequest request) throws BusinessException;
	
}
