package com.match.htservice.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.htservice.AuthService;
import com.match.service.DBAuthUserService;
import com.util.DateUtils;
import com.util.EncryptUtil;
import com.util.RequestUtil;
import com.util.exception.BusinessException;



@Service
public class AuthServiceImpl implements AuthService {

	private final static Logger log = LoggerFactory.getLogger(AuthService.class);


	@Autowired
	private DBAuthUserService authUserService;

	/**
	 * 用户登陆
	 */
	public void userLogin(Map<String, Object> param, HttpServletRequest request)
			throws BusinessException {
		HttpSession session = request.getSession();
		String pwd = EncryptUtil.getEncrypPwd(param.get("userId"),
				param.get("userPwd"));
		log.debug("密码加密[{}]", pwd);
		param.put("userPwd", pwd);
		param.put("sysId", "0001");
		Map<String, Object> authUser = authUserService.selectAuthUser(param);
		if (authUser != null) {
			// 判断用户名是否呗禁用
			if (authUser.get("userStatus").equals(0)) {
				log.info("用户状态:1:启用,2:禁用[{}]", authUser.get("userStatus"));
				request.setAttribute("error", "登陆失败，账号未启用");
				throw new BusinessException("登陆失败，账号未启用");
			} else {
				// 判断验证码是否正确,如果不正确,页面提示错误信息,异常
				if (!((String) param.get("code"))
						.equalsIgnoreCase((String) param.get("codeUp"))) {
					log.info("验证码错误：[{}]");
					request.setAttribute("error", "验证码错误");
					throw new BusinessException("验证码错误");
				} else {
					log.debug("登陆者信息[{}]", authUser);
					session.setAttribute("userId", authUser.get("userId"));
					session.setAttribute("id", authUser.get("id"));
					session.setAttribute("sysId", authUser.get("sysId"));
					String ip = RequestUtil.getIpAddress(request);
					String date = DateUtils.getCurrentDateTime2();// 获取当前时间
					Map<String, Object> maps = new HashMap<String, Object>();
					maps.put("id", authUser.get("id"));
					maps.put("lastLoginIp", ip);
					maps.put("lastLoginTime", date);
					authUserService.updateAuthUser(maps);
					log.info("登陆IP:[{}],登陆时间:[{}]", ip, date);
				}
			}
		} else {
			log.info("登陆参数[{}]", param);
			request.setAttribute("error", "登陆失败，请检查用户名或密码");
			throw new BusinessException("登陆失败，请检查用户名或密码");
		}
	}

	public boolean updateUserPwd(Map<String, Object> param,
			HttpServletRequest request) throws BusinessException {
		HttpSession session = request.getSession();
		log.debug("正在验证原密码...");
		String userId = session.getAttribute("userId") + "";
		String userPwd = param.get("userPwd") + "";
		String pwd = EncryptUtil.getEncrypPwd(userId, userPwd);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		Map<String, Object> authUser = authUserService.selectAuthUser(paramMap);
		try {
			if (!authUser.get("userPwd").equals(pwd)) {
				log.debug("错误原密码:加密前[{}]，加密后[{}]", userPwd, pwd);
				throw new BusinessException("修改失败，原密码验证错误");
			}
			userPwd = EncryptUtil.getEncrypPwd(userId, param.get("pwd"));
			paramMap.put("userPwd", userPwd);
			paramMap.put("id", authUser.get("id"));
			log.debug("原密码验证成功,修改新密码,参数[{}]", paramMap);
			authUserService.updateAuthUser(paramMap);
			log.debug("修改完成...");
		} catch (Exception e) {
			log.debug("验证密码出现异常");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
