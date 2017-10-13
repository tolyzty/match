package com.match.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.MenuDao;
import com.match.service.DBMenuService;


@Service
public class DBMenuServiceImpl implements DBMenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public List<Map<String, Object>> getMenuList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return menuDao.getMenuList(param);
	}

	@Override
	public List<Map<String, Object>> getAllMenuList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return menuDao.getAllMenuList(param);
	}

	@Override
	public List<Map<String, Object>> getMenuByID(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return menuDao.getMenuByID(param);
	}

	@Override
	public void updateMenu(Map<String, Object> param) {
		// TODO Auto-generated method stub
		menuDao.updateMenu(param);
	}

	@Override
	public void insertMenu(Map<String, Object> param) {
		// TODO Auto-generated method stub
		menuDao.insertMenu(param);
	}

	@Override
	public Integer queryMaxId(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return menuDao.queryMaxId(param);
	}

}
