package com.match.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.OrderDao;
import com.match.service.DBOrderService;

@Service
public class DBOrderServiceImpl implements DBOrderService {
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public List<Map<String, Object>> getOrderList(Map<String, Object> param) {
		
		return this.orderDao.getOrderList(param);
	}

	@Override
	public int inserOrder(Map<String, Object> param) {
		
		return this.orderDao.inserOrder(param);
	}

	@Override
	public Map<String, Object> findByOrder(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.orderDao.findByOrder(param);
	}

	@Override
	public int updateOrder(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.orderDao.updateOrder(param);
	}

	@Override
	public Map<String, Object> orderByLimit(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.orderDao.orderByLimit(param);
	}

	@Override
	public int updateJoinByOrder(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.orderDao.updateJoinByOrder(param);
	}

	@Override
	public Map<String, Object> queryByOrderAndJoin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.orderDao.queryByOrderAndJoin(param);
	}
}
