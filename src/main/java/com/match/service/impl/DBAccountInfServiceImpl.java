package com.match.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.match.dao.AccountInfDao;
import com.match.service.DBAccountInfService;
import com.match.service.DBAccountService;
import com.match.service.DBOrderService;
import com.util.DateUtils;
import com.util.MapUtils;
import com.util.MoneyUtils;
import com.util.constants.Constants;


@Service
public class DBAccountInfServiceImpl implements DBAccountInfService {
	private static Logger log = LoggerFactory
			.getLogger(DBAccountInfServiceImpl.class);
	
	@Autowired
	private AccountInfDao accountInfDao;
	
	@Autowired
	private DBOrderService dbOrderService;
	
	@Autowired
	private DBAccountService dbAccountService;
	
	@Autowired
	private DBAccountInfService dbAccountInfServie;

	@Override
	public List<Map<String, Object>> getAccountList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountInfDao.getAccountList(param);
	}

	@Override
	public int inserAccountInf(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountInfDao.inserAccountInf(param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addAccountHis(Map<String, Object> param) throws Exception {
		log.info("开始添加余额变动日志:[{}]",param);
		MapUtils.doing(param, "changeType", "consumerNo","orderNo");
		Map<String, Object> reMap = new HashMap<String, Object>();
		int rt=0;
		try {
			log.info("[根据用户编号查询accountInf最后一条日志记录..]");
			reMap.put("consumerNo", param.get("consumerNo"));//用户编号
			reMap.put("muType", param.get("muType"));//项目类别
			reMap.put("agentId", param.get("agentId"));//推荐人
			log.info("查询accountInf记录参数:[{}]",reMap);
			Map<String, Object> findMap = dbAccountInfServie.findByQuery(reMap);		
			log.info("获得accountInf查询结果:[{}]",findMap);
			double su=0;
			double resu=0;
			if (findMap!=null) {
				su = Double.parseDouble(findMap.get("acBal").toString());	
				log.info("acBal金额:[{}]",su);
			}
			resu = Double.parseDouble(param.get("payAmt").toString());
			String totalAccount = param.get("consumerNo")+""+param.get("muType")+"";
			String dateTime = DateUtils.getCurrentDateTime2();//时间
			log.info("accountNo信息:[{}]",totalAccount);
			reMap.put("orderNo", System.currentTimeMillis() + "");	//订单编号	
			reMap.put("accountNo", totalAccount);// 总账户账户号为商户号+01
			reMap.put("requestAmt", param.get("payAmt"));//请求金额
			reMap.put("requestTime",DateUtils.getCurrentDateTime2());//请求时间
			reMap.put("orderStatus", param.get("orderStatus")); //交易状态
			reMap.put("muType", param.get("muType")); //交易类型
			reMap.put("payTime", DateUtils.getCurrentDateTime2());//交易时间,获取的当前时间
			reMap.put("payAmt", param.get("payAmt"));
			reMap.put("orderType", param.get("orderType"));//findMap.get("acBal")
			reMap.put("changeType",param.get("payType"));//充值类型
			reMap.put("acBalcz", param.get("payAmt")); //操作金额
			reMap.put("oldAcBal", su); //根据商户编号查询到的原资金总额		
			reMap.put("acBal", su+resu); //现在资金总额	
			reMap.put("lstTxDatetime", dateTime);//提交时间
			reMap.put("logDateTime", dateTime); //日志记录时间
			reMap.put("accOrderNo", param.get("orderNo"));//交易订单号
			reMap.put("changeDesc", param.get("orderDesc"));//交易订单号
			reMap.put("agentId", param.get("agentId"));//推荐人ID默认是1000120001：
			reMap.put("orderStatus", param.get("orderStatus"));//支付状态 0:待支付 1:已经支付
			log.info("所有添加的参数:[{}]",reMap);
			int u = accountInfDao.inserAccountInf(reMap);
			log.info("保存日志文件:[{}]",u);
			rt = dbAccountService.addAccountAmt(reMap);
			/*if (u!=0 || !("0").equals(reMap.get("orderStatus"))) {
				rt = dbAccountService.addAccountAmt(reMap);
			}*/
			log.info("帐户入账操作状态:[{}]",rt);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception("账户变更历史添加异常",e);
		}
		if (rt==0) {	
			throw new Exception("账户变更历史添加失败");
		}
		return rt;
	}

	@Override
	public int modifyAccountAmt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> findByQuery(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.accountInfDao.findByQuery(param);
	}

	@Override
	public int addAccountAmt(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findCountInf(Map<String, Object> param) {
		
		return this.accountInfDao.findCountInf(param);
	}


}
