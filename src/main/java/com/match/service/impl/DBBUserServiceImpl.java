package com.match.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.BUserDao;
import com.match.service.DBBUserService;


@Service
public class DBBUserServiceImpl implements DBBUserService {
	
	
	@Autowired
	private BUserDao bUserDao;
	
	
	@Override
	public List<Map<String, Object>> getUserList(Map<String, Object> param) {
		
		return this.bUserDao.getUserList(param);
	}


	@Override
	public int registUser(Map<String, Object> param) {
		return this.bUserDao.registUser(param);
	}


	@Override
	public Map<String, Object> queryUserByAll(Map<String, Object> param) {
		
		return this.bUserDao.queryUserByAll(param);
	}


	@Override
	public int updateUser(Map<String, Object> prarm) {
		// TODO Auto-generated method stub
		return this.bUserDao.updateUser(prarm);
	}


	@Override
	public int getUserListCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.bUserDao.getUserListCount(param);
	}


}
