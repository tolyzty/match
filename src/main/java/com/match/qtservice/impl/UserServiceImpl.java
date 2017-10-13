package com.match.qtservice.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.match.qtservice.UserService;
import com.match.service.DBBUserService;
import com.util.DateUtils;
import com.util.EncryptUtil;
import com.util.constants.Constants;
import com.util.exception.BusinessException;


@Service
public class UserServiceImpl implements UserService {
	
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private DBBUserService userService;

	@Override
	public void userLogin(Map<String, Object> param, HttpServletRequest request)
			throws BusinessException {
		log.info("登录参数：[{}]",param);
		HttpSession session = request.getSession();
		String pwd = EncryptUtil.getEncrypPwd(param.get("userPhone"),param.get("userPassword"));
		log.debug("密码加密[{}]",pwd);
		param.put("userPassword", pwd);
		log.info("param:[{}]",param);
		Map<String , Object> authUser =userService.queryUserByAll(param);
		log.info("验证用户名和密码：[{}]",authUser);
		if (authUser==null) {
			request.setAttribute("error", "手机号或密码错误");
			throw new BusinessException("手机号或密码错误");
		}else{
			log.info("记录SESSION：[{}]",authUser.get("cousumerNo"));
			session.setAttribute("consumerNo", authUser.get("consumerNo"));
			session.setAttribute("userPhone", authUser.get("userPhone"));
			//session.setAttribute("wzToken", authUser.get("wzToken"));
			session.setAttribute("userId", authUser.get("userId"));
			session.setAttribute("agentId", authUser.get("agentId"));
			session.setAttribute("islockId", authUser.get("islockId"));
			session.setAttribute("islock", authUser.get("islock"));
			/*new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			}).start();  另外开起线程执行*/
			try {
				String date = DateUtils.getCurrentDateTime2();
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("userId", authUser.get("userId"));
				maps.put("lastLoginTime", date);
				int u = userService.updateUser(maps);
				log.info("记录登录时间:[{}],记录是否成功:[{}]",date,u);
			} catch (Exception e) {
				log.warn("记录最后登录时间异常：[{}]",e);
			}
		}
	}

	@Override
	public void register(Map<String, Object> param, HttpServletRequest request)
			throws BusinessException {
		log.info("开始注册...");
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void queryAndUpdateUser(Map<String, Object> param,
			HttpServletRequest request) throws BusinessException {
		log.info("开始执行查询上级推荐人信息");
		log.info("上级推荐人信息参数:[{}]",param);
		Map<String, Object> isMap = new HashMap<String, Object>();
		try {	
			if (("old").equals(param.get("orderType"))) {
				throw new Exception();
			}
			Map<String, Object> queryUser = userService.queryUserByAll(param);
			log.info("查询的参数:[{}]",queryUser);
			if(queryUser==null){
				log.info("[异常错误]用户,二级用户,不参与奖励");
				throw new Exception();
			}
			if (queryUser.get("islock").equals(1)) {
					log.info("[异常错误]推荐人,不参与奖励");
					throw new Exception();
			}
			
			/*if (!("2").equals(queryUser.get("islock"))) {
				log.info("[异常错误]用户为推荐人,自己加入不参与奖励");
				throw new Exception(); 
			}*/
			
			if (queryUser.get("awardFlag")!="1") {
				log.info("如果查询的用户未做过奖励,开始执行查询,编辑");
				isMap.put("islockId", queryUser.get("islockId"));
				isMap.put("islock", Constants.ISLOCK_STATUS_SU);
				log.info("查上级推荐人参数:[{}]",isMap);
				Map<String, Object> isfindUser = userService.queryUserByAll(isMap);
				log.info("查询上级推荐人:[{}]",isfindUser);
				log.info("原金额:[{}]",isfindUser.get("islockAmt"));
				if (isfindUser!=null) {
					double awardAmt = 0;
					double islockAmt = 0;
					awardAmt = Double.parseDouble(isfindUser.get("awardAmt").toString());
					islockAmt = Double.parseDouble(isfindUser.get("islockAmt").toString());
					log.info("设置的推荐金额:[{}],总推荐金额:[{}]",awardAmt,islockAmt);
					isMap.clear();
					isMap.put("islockAmt", islockAmt+awardAmt);
					isMap.put("userId", isfindUser.get("userId"));
					int u = userService.updateUser(isMap);
					isMap.clear();
					isMap.put("awardFlag", "1");
					isMap.put("userId", queryUser.get("userId"));
					u = userService.updateUser(isMap);
					if (u==0) {
						throw new BusinessException("修改推荐金额失败");
					}
					log.info("开始执行修改推荐金额");
				}
			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
