package com.match.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.JoinDao;
import com.match.service.DBJoinService;

@Service
public class DBJoinServiceImpl implements DBJoinService {
	
	@Autowired
	private JoinDao joinDao;
	

	@Override
	public List<Map<String, Object>> getJoinList(Map<String, Object> param) {
		
		return this.joinDao.getJoinList(param);
	}


	@Override
	public int addJoin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.addJoin(param);
	}


	@Override
	public Map<String, Object> findByJoin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.findByJoin(param);
	}


	@Override
	public Map<String, Object> queryByLimit(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.queryByLimit(param);
	}


	@Override
	public int updateJoin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.updateJoin(param);
	}


	@Override
	public List<Map<String, Object>> JoinList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.JoinList(param);
	}


	@Override
	public List<Map<String, Object>> queryByJoinAndAcc(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.queryByJoinAndAcc(param);
	}


	@Override
	public int updateItemJoin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.updateItemJoin(param);
	}


	@Override
	public List<Map<String, Object>> queryByJoinitem(Map<String, Object> param) {
		
		return this.joinDao.queryByJoinitem(param);
	}


	@Override
	public int getJoinCount(Map<String, Object> param) {
		
		return this.joinDao.getJoinCount(param);
	}


	@Override
	public int updateJoinByAmt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.updateJoinByAmt(param);
	}


	@Override
	public Map<String, Object> queryByJoinAndMu(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.queryByJoinAndMu(param);
	}


	@Override
	public int updateNotBal(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinDao.updateNotBal(param);
	}



}
