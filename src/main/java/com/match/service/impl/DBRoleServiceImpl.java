package com.match.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.RoleDao;
import com.match.service.DBRoleService;




@Service
public class DBRoleServiceImpl implements DBRoleService {
	
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Map<String, Object>> findAll() {
		
		return this.roleDao.findAll();
	}

	@Override
	public int insertRole(Map<String, Object> role) {
		
		return this.roleDao.insertRole(role);
	}

	@Override
	public int updateById(Map<String, Object> role) {
		
		return this.roleDao.updateById(role);
	}

	@Override
	public List<Map<String, Object>> findById(Map<String, Object> role) {
		
		return this.roleDao.findById(role);
	}

	@Override
	public List<Map<String, Object>> findByKayId(Map<String, Object> role) {
		
		return this.roleDao.findByKayId(role);
	}

	@Override
	public int deletes(Map<String, Object> role) {
		
		return this.roleDao.deletes(role);
	}

	@Override
	public List<Map<String, Object>> findByPage(Map<String, Object> role) {
		return this.roleDao.findByPage(role);
	}

	@Override
	public int findByCount(Map<String, Object> role) {
		// TODO Auto-generated method stub
		return this.roleDao.findByCount(role);
	}

	@Override
	public List<Map<String, Object>> findRoleMenu(Map<String, Object> role) {
		// TODO Auto-generated method stub
		return this.roleDao.findRoleMenu(role);
	}

	@Override
	public List<Map<String, Object>> findMenu() {
		// TODO Auto-generated method stub
		return this.roleDao.findMenu();
	}

	@Override
	public int insertCheckData(Map<String, Object> rolemenu) {
		
		return this.roleDao.insertCheckData(rolemenu); 
	}

	@Override
	public int deleteMenu(Map<String, Object> role) {
		// TODO Auto-generated method stub
		return this.roleDao.deleteMenu(role);
	}

	




}
