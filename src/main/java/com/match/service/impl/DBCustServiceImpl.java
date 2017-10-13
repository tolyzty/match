package com.match.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.CustDao;
import com.match.service.DBCustService;

@Service
public class DBCustServiceImpl implements DBCustService {
	
	@Autowired
	private CustDao custDao;

	@Override
	public List<Map<String, Object>> getCustList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.custDao.getCustList(param);
	}

	@Override
	public int getCustCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.custDao.getCustCount(param);
	}

	@Override
	public int addCust(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.custDao.addCust(param);
	}

	@Override
	public int updateCust(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.custDao.addCust(param);
	}

	@Override
	public Map<String, Object> queryCust(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.custDao.queryCust(param);
	}


	

	

}
