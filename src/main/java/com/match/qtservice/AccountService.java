package com.match.qtservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.util.exception.BusinessException;

public interface AccountService {
	
	
	/**
	 * 执行帐户和加入信息
	 * @param param
	 * @return
	 */
	int updateAccAndJoin(Map<String, Object> param,HttpServletRequest request)throws BusinessException, Exception;

	
	/**
	 * 新增帐户,新增余额变动记录
	 * @param param
	 * @param request
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	int insertAccAndAccInf(Map<String, Object> param,HttpServletRequest request)throws BusinessException, Exception;
	/**
	 * 余额不足充值,修改ACC表,修改ACCInf表
	 * @param param
	 * @param request
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	int updateAccAndAccInf(Map<String, Object> param,HttpServletRequest request)throws BusinessException, Exception;
}
