package com.match.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.MutualDao;
import com.match.service.DBMutualService;

@Service
public class DBMutualServiceImpl implements DBMutualService {
	
	@Autowired
	private MutualDao mutualDao;

	public List<Map<String, Object>> getMutualList(Map<String, Object> param) {
		
		return this.mutualDao.getMutualList(param);
	}

	@Override
	public Map<String, Object> findMutual(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.mutualDao.findMutual(param);
	}

	@Override
	public int updateMutual(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.mutualDao.updateMutual(param);
	}

	@Override
	public int updateMutualHt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.mutualDao.updateMutualHt(param);
	}

	@Override
	public int insertMutualHt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.mutualDao.insertMutualHt(param);
	}

}
