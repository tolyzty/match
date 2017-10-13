package com.match.htservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.util.exception.BusinessException;



public interface AuthService {
	
	void userLogin(Map<String, Object> param,HttpServletRequest request)throws BusinessException;

	/**
	 * 操作员密码修改
	 * @param param
	 * @param request
	 * @throws BusinessException
	 */
	boolean updateUserPwd(Map<String, Object> param,HttpServletRequest request) throws BusinessException;

	
}
