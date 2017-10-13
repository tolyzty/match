package com.match.qtservice.impl;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.match.qtservice.MutualService;
import com.match.service.DBJoinService;
import com.match.service.DBMutualService;
import com.sun.org.apache.bcel.internal.generic.IREM;
import com.util.MapUtils;
import com.util.exception.BusinessException;
@Service
public class MutualServiceImpl implements MutualService {
	
	private final static Logger log = LoggerFactory.getLogger(MutualServiceImpl.class);
	
	@Autowired
	private DBMutualService dbMutualService;
	@Autowired
	private DBJoinService joinService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateMu(Map<String, Object> param, HttpServletRequest request) throws Exception {
	//log.info("执行修改加入[join]status...");
		log.info("获取的所有参数:[{}]",param);
		int rt = 0;
		Map<String, Object> midMap = new HashMap<String, Object>();
		try {
			/*midMap.put("joinAmt", param.get("requestAmt"));//请求金额
			midMap.put("mutualType",param.get("muType"));//项目类型
			midMap.put("consumerNo", param.get("consumerNo"));
			midMap.put("orderType", param.get("orderType"));*/
			/*edMap.put("joinStatus", param.get("joinStatus"));
			edMap.put("joinZt", param.get("joinZt"));
			edMap.put("consumerNo", param.get("consumerNo"));
			edMap.put("agentId", param.get("agentId"));
			log.info("封装的参数:[{}]",edMap);
			int jo = joinService.updateJoin(edMap);
			log.info("编辑join的状态..[{}]",jo);
			if (jo==0) {
				throw new Exception("修改加入Status[join]失败,");
			}*/
			MapUtils.doing(param, "muType","requestAmt");
			double mAmt=0;
			double parAmt = Double.parseDouble(param.get("requestAmt").toString());
			double mutualAmt = 0;
			log.info("加入者申请的金额：[{}]",parAmt);
			midMap.put("mutualType", param.get("muType"));
			Map<String, Object> muMap = dbMutualService.findMutual(midMap);
			log.info("查询ID的项目：[{}]",muMap);
			if (muMap==null) {
				throw new BusinessException("项目不存在");
			}
			mAmt = Double.parseDouble(muMap.get("mutualAmt").toString());//项目总金额
			log.info("项目已经加入的金额总数:[{}]",mAmt);
			mutualAmt = parAmt + mAmt;
			log.info("更新前项目总金额:[{}],加入人申请金额:[{}]",mAmt,parAmt);
			log.info("更新后的总金额:[{}]",mutualAmt);
			midMap.put("mutualAmt", mutualAmt);
			midMap.put("mutualType", muMap.get("mutualType"));		
		if (("new").equals(param.get("orderType"))) {
				int mutualNumber = Integer.parseInt(muMap.get("mutualNumber").toString())+1;
				log.info("原加入项目总人数:[{}]",muMap.get("mutualNumber"));
				log.info("更新后加入项目总人数:[{}]",mutualNumber);
				midMap.put("mutualNumber", mutualNumber);	
		}else{
			 log.info("已有帐户充值,只修改金额:[{}]",mutualAmt);
			 midMap.put("mutualNumber", muMap.get("mutualNumber"));
			 log.info("已有帐户充值,加入人数：[{}]",muMap.get("mutualNumber"));
		}
		log.info("执行编辑的所有参数:[{}]",midMap);
		rt = dbMutualService.updateMutual(midMap);
		if (rt==0) {
			throw new Exception("修改项目[mutual]失败");
		}
	
		} catch (BusinessException e) {
			log.warn("修改项目和修改加入状态失败",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return rt;
	}

}
