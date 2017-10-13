package com.match.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.match.dao.AccountDao;
import com.match.dao.OrderDao;
import com.match.service.DBAccountService;
import com.match.service.DBOrderService;
import com.util.DateUtils;
import com.util.MapUtils;
import com.util.constants.Constants;

@Service
public class DBAccountServiceImpl implements DBAccountService {
	
	private static Logger log = LoggerFactory
			.getLogger(DBAccountServiceImpl.class);
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public List<Map<String, Object>> getAccountLists(Map<String, Object> param) {
		return this.accountDao.getAccountLists(param);
	}
	
	@Override
	public int inserAccount(Map<String, Object> param) {
		return this.accountDao.inserAccount(param);
	}

	@Override
	public int modifyAccountAmt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAccountAmt(Map<String, Object> param) throws Exception {
		Map<String, Object> isacMap = new HashMap<String, Object>();
		log.info("执行account帐户表：[{}]",param);
		String dateTime = DateUtils.getCurrentDateTime2();//时间
		log.info("执行帐户时间:[{}]",dateTime);
		MapUtils.doing(param, "consumerNo","acBal","muType");
		isacMap.put("consumerNo", param.get("consumerNo"));
		isacMap.put("muType", param.get("muType"));//项目类别
		isacMap.put("agentId", param.get("agentId"));
		log.info("根据参数查询account表:[{}]",isacMap);
		Map<String, Object> findMap = accountDao.findByQueryAmt(isacMap);
		//log.info("根据商户编号和项目,查询帐户信息:[{}]",findMap);	
		isacMap.put("accountNo", param.get("accountNo"));//帐户编号consumer_no,
		isacMap.put("acBal", param.get("acBal"));//总余额
		isacMap.put("lstTxDatetime", dateTime);//最后修改时间	
		isacMap.put("agentId", param.get("agentId"));
		isacMap.put("orderStatus", param.get("orderStatus"));
		log.info("新增帐户全部信息:[{}]",isacMap);
		log.info("新增帐户信息推荐ID：[{}]",isacMap.get("agentId"));
		int u=0;
		try {
			if (findMap==null) {
				log.info("新账户,开始建立帐户");
				u = accountDao.inserAccount(isacMap);
				log.info("帐户建立状态:[{}]",u);
			}else{
				log.info("已有帐户,入账操作....");
				u = accountDao.updateAccount(isacMap);
				log.info("编辑帐户状态:[{}]",u);
			}
			
		} catch (Exception e) {
			throw new Exception("账户编辑异常",e);
		}
		if (u==0) {
			throw new Exception("帐户添加异常");
		}
		return u;
	}

	@Override
	public Map<String, Object> findByQueryAmt(Map<String, Object> param) {
		
		return this.accountDao.findByQueryAmt(param);
	}

	@Override
	public int updateAccount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountDao.updateAccount(param);
	}

	@Override
	public int updateAcc(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountDao.updateAcc(param);
	}

	@Override
	public int updateAccByAccInf(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountDao.updateAccByAccInf(param);
	}

	@Override
	public int findCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountDao.findCount(param);
	}

	@Override
	public int updateQueyAmt(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountDao.updateQueyAmt(param);
	}



}
