package com.match.qtservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.util.exception.BusinessException;

public interface MutualService {

	

	
	/**
	 * 编辑的Service
	 * @param param
	 * @param request
	 * @throws BusinessException 
	 * @throws Exception 
	 */
	int updateMu(Map<String, Object> param,HttpServletRequest request) throws BusinessException, Exception;
}
