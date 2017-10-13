package com.match.qtservice.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.match.qtservice.AccountService;
import com.match.qtservice.MutualService;
import com.match.service.DBAccountInfService;
import com.match.service.DBAccountService;
import com.match.service.DBJoinService;
import com.util.DateUtils;
import com.util.constants.Constants;
import com.util.exception.BusinessException;
@Service
public class AccountServiceImpl implements AccountService {

	private final static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private DBJoinService dbJoinService;
	@Autowired
	private DBAccountInfService dbAccountInfService;
	@Autowired
	private DBAccountService dbAccountService;
	@Autowired
	private MutualService mutualService;

	@Override
	public int updateAccAndJoin(Map<String, Object> param,
			HttpServletRequest request) throws BusinessException, Exception {
		log.info("执行帐户和加入表的修改操作参数:[{}]",param);
		HttpSession session = request.getSession();
		Map<String, Object> acMap = new HashMap<String, Object>();
		Map<String, Object> joMap = new HashMap<String, Object>();
		try {
			int ut = dbAccountService.updateAcc(param);
			if (ut==0) {
				throw new Exception("修改帐户异常");
			}
			Map<String, Object> upMap = new HashMap<String, Object>();
			//reMap.clear();
			upMap.put("mutualType",param.get("mutualType"));
			upMap.put("joinAmt", param.get("payAmt"));
			upMap.put("agentId", param.get("agentId"));
			upMap.put("consumerNo", param.get("consumerNo"));
			upMap.put("joinStatus", param.get("orderStatus"));
			upMap.put("joinZt", Constants.JOINSTATUS_1);//修改加入表和项目成功
			upMap.put("orderType", param.get("orderType"));
			log.info("执行修改Update参数",upMap);
			int mut = mutualService.updateMu(upMap, request);
		} catch (Exception e) {
			log.warn("保存帐户和加入信息异常:[{}]",e);
			e.printStackTrace();
		}
		
		
		
		
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int insertAccAndAccInf(Map<String, Object> param,
			HttpServletRequest request) throws BusinessException, Exception {
		log.info("开始执行[account]和[accountInf]记录,加入项目执行");
		log.info("获取参数：[{}]",param);
		int uacinf = 0;
		Map<String, Object> parMap = new HashMap<String, Object>();
		try {
			parMap.put("consumerNo", param.get("consumerNo"));
			parMap.put("accountNo", param.get("consumerNo")+param.get("muType").toString());
			parMap.put("agentId", param.get("agentId"));
			parMap.put("muType", param.get("muType"));
			parMap.put("acBal", param.get("payAmt"));
			parMap.put("lstTxDatetime", DateUtils.getCurrentDateTime2());
			parMap.put("orderStatus", param.get("orderStatus"));
			log.info("执行保存[account]参数:[{}]",parMap);
			int uac = dbAccountService.inserAccount(parMap);
			if (uac==0) {
				throw new Exception("保存帐户信息异常");
			}
			double oldAcBal = 0;
			double oldFrozBalance = 0;
			parMap.put("oldAcBal", oldAcBal);
			parMap.put("acBalcz", param.get("payAmt"));
			parMap.put("oldFrozBalance", oldFrozBalance);
			parMap.put("changeType", param.get("changeType"));
			//parMap.put("accOrderNo", param.get("payOrderNo"));
			parMap.put("changeDesc", param.get("orderDesc"));
			parMap.put("logDateTime", DateUtils.getCurrentDateTime2());
			log.info("执行保存[accountInf]参数:[{}]",parMap);
		    uacinf = dbAccountInfService.inserAccountInf(parMap);
			if (uacinf==0) {
				throw new Exception("保存余额变动信息异常");
			}
		} catch (Exception e) {
			log.warn("执行保存acc和inf出错异常:[{}]",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return uacinf;
	}
		/*
		*
		*
		* 获取参数：[{joinName=兰陵王, 
		* joinCard=111111122, payType=1, mutualId=1, muType=02, 
		* mutualVipzg=01, mutualUnder=180, orderType=new, payAmt=500, 
		* agentId=100020001, muId=1, userId=23, orderNo=1500004541631, consumerNo=100020, 
		* orderDesc=互动会员充值, changeType=weixin, orderStatus=0}]
		 */
		/*int uacinf = 0;
		try {
			Map<String, Object> parMap = new HashMap<String, Object>();
			//20170714:09:45:19.381 INFO  获取参数：
			//[{agentId=100021010, orderId=185, userId=24, orderNo=1499996484424, payOrderNo=888888888888, 
			//consumerNo=100021, requestAmt=500.00, requestTime=2017-07-14 09:41:26.0, 
			//successTime=2017-07-14 09:42:54.0, orderStatus=1, payTime=2017-07-14 09:41:26.0, 
			//payAmt=500.00, orderType=new, orderDype=互动会员充值, muType=02, payType=1}]
			
			
		} catch (Exception e) {
			log.warn("执行保存acc和inf出错异常:[{}]",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}*/
		
		//int uacinf = 
		
		/*Map<String, Object> paramAc = new HashMap<String, Object>();
		log.info("account帐户表修改:[{}]",paramMap.get("agentId"));
		paramAc.put("agentId", paramMap.get("agentId"));
		Map<String, Object> acMap = dbAccountService.findByQueryAmt(paramAc);
		log.info("根据AgentId查找帐户信息:[{}]",acMap);
		paramAc.clear();
		paramAc.put("accountId", acMap.get("accountId"));//ID
		paramAc.put("agentId", paramMap.get("agentId"));
		paramAc.put("consumerNo", acMap.get("consumerNo"));
		paramAc.put("lstTxDatetime", DateUtils.getCurrentDateTime2());//时间
		String requestAmt = resMap.get("payAmt")+""; //金额
		String oldrequesAmt = acMap.get("acBal")+"";//原金额
		double su=0;
		double resu=0;
		if (acMap!=null) {
			su = Double.parseDouble(oldrequesAmt);	
			log.info("acBal金额:[{}]",su);
		}
		resu = Double.parseDouble(requestAmt);
		log.info("充值金额:[{}]",resu);
		paramAc.put("oldAcBal", su);;
		paramAc.put("payAmt", resu);
		paramAc.put("acBal", su+resu);
		paramAc.put("orderStatus", Constants.PAY_ORDER_STATUS_SUC);
		paramAc.put("mutualType",  resMap.get("muType"));
		paramAc.put("orderType", resMap.get("orderType"));
		log.info("修改帐户的参数:[{}]",paramAc);
		int ut = accountService.updateAccAndJoin(paramAc, request);
		//int ut = dbAccountService.updateAcc(paramAc);
		if (ut==0) {
			throw new Exception("修改帐户金额失败");
		}*/


	@Override
	public int updateAccAndAccInf(Map<String, Object> param,
			HttpServletRequest request) throws BusinessException, Exception {
		log.info("开始执行[account修改] 和 [accountInf修改],余额不足充值执行");
		log.info("获取的所有修改帐户参数:[{}]",param);
		int upac = 0;
		int mut = 0;
		try {
			Map<String, Object> parMap = new HashMap<String, Object>();
			parMap.put("orderStatus", Constants.PAY_ORDER_STATUS_SUC);
			parMap.put("agentId", param.get("agentId"));
			if (param.get("orderType").equals("old")) {
				parMap.put("acBal", param.get("payAmt"));
			}
			log.info("编辑帐户:[{}]",parMap);
			upac = dbAccountService.updateAccByAccInf(parMap);
			if (upac==0) {
				throw new Exception("帐户修改异常,请检查");
			}
			param.put("orderStatus", Constants.PAY_ORDER_STATUS_SUC);
			mut = mutualService.updateMu(param, request);				
		} catch (Exception e) {
			log.warn("修改帐户异常");
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}if (mut==0) {
			throw new Exception("修改项目金额人数异常");
		}		
		
		return upac;
	}

}
