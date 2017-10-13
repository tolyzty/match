package com.match.service.impl;

import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.dao.AuthUserDao;
import com.match.service.DBAuthUserService;





@Service
public class DBAuthUserServiceImpl implements DBAuthUserService {

	@Autowired
	private AuthUserDao authUserDao;
	

	@Override
	public List<Map<String, Object>> findByUserPage(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.findByUserPage(param);
	}

	@Override
	public int getUserCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.getUserCount(param);
	}

	@Override
	public int insertAuthUser(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.insertAuthUser(param);
	}

	@Override
	public List<Map<String, Object>> findByUser(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.findByUser(param);
	}

	@Override
	public int updateAuthUser(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.updateAuthUser(param);
	}

	@Override
	public Map<String, Object> selectAuthUser(Map<String, Object> param) {
		
		return this.authUserDao.selectAuthUser(param);
	}

	@Override
	public List<Map<String, Object>> findUserMenu(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.findUserMenu(param);
	}

	@Override
	public List<Map<String, Object>> findRolMenu() {
		// TODO Auto-generated method stub
		return this.authUserDao.findRolMenu();
	}

	@Override
	public int insertUserData(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.insertUserData(param);
	}

	@Override
	public int deleteUserMenu(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.authUserDao.deleteUserMenu(param);
	}

	@Override
	public List<Map<String, Object>> queryByUser(Map<String, Object> param) {
		
		return this.authUserDao.queryByUser(param);
	}
	


}
