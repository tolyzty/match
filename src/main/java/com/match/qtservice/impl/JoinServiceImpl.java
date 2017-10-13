package com.match.qtservice.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.match.qtservice.JoinService;
import com.match.service.DBJoinService;
import com.util.DateUtils;
import com.util.MapUtils;
import com.util.exception.BusinessException;
@Service
public class JoinServiceImpl implements JoinService {
	
	private final static Logger log = LoggerFactory.getLogger(JoinServiceImpl.class);
	
	@Autowired
	private DBJoinService joinService;

	@Override
	public int addJoin(Map<String, Object> param, HttpServletRequest request)
			throws BusinessException {
		 log.info("开始查询join表用户是否有推荐");
		 int u = 0; 
      try {
		 MapUtils.doing(param, "consumerNo");
		 String consumerNo = param.get("consumerNo")+"";
		 log.info("商户编号:[{}]",consumerNo);		 
		 log.info("开始新增加入....");
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		 int overdate = Integer.parseInt(param.get("mutualUnder").toString()) ;
		 log.info("会员观察天数:[{}]",overdate);
		 String date = DateUtils.getCurrentDateTime2();//当前时间
		 String Format = DateUtils.DATETIME_FORMAT_4;//时间格式
		 String vipdate =DateUtils.dateAddDay(date, Format, overdate);//日期相加
		 log.info("会员观察结束日期:[{}]",vipdate);
		
			 paramMap.put("muId", param.get("mutualId"));//用户ID
			 paramMap.put("consumerNo", consumerNo);//用户编号
			 paramMap.put("eventNubmer", "SJ"+System.currentTimeMillis() + "");//事件编号
			 paramMap.put("joinName", param.get("joinName"));//申请人姓名
			 paramMap.put("joinCard", param.get("joinCard"));//申请人卡号
			 paramMap.put("joinAmt", param.get("payAmt"));//扣除的金额
			 paramMap.put("joinVipzg", param.get("mutualVipzg"));//会员资格
			 paramMap.put("joinTime", date);//加入时间
			 paramMap.put("joinVipsuTime", vipdate);//观察结束时间
			 paramMap.put("joinEffetime", param.get("mutualUnder"));//观察结束天数
			 paramMap.put("joinMutype", param.get("muType"));
			 paramMap.put("agentId", param.get("agentId"));
			 paramMap.put("orderType", param.get("orderType"));
			 log.info("新增加入参数:[{}]",paramMap);
			 u = joinService.addJoin(paramMap);
		 if (u==0) {
			 throw new Exception("帐户添加异常");
		 }	
		} catch (Exception e) {
			log.warn("新增加入信息失败",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		 return u;

	}

	@Override
	public Map<String, Object> findByJoin(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.joinService.findByJoin(param);
	}

	@Override
	public int editJoin(Map<String, Object> param, HttpServletRequest request)
			throws BusinessException {
		log.info("获取所有join参数:[{}]",param);
		Map<String, Object> edMap = new HashMap<String, Object>();
		edMap.put("joinStatus", "1");
		edMap.put("consumerNo", param.get("consumerNo"));
		edMap.put("agentId", param.get("agentId"));
		log.info("封装的参数:[{}]",edMap);
		int jo = joinService.updateJoin(edMap);
		log.info("编辑join的状态..[{}]",jo);
		return jo;
	}

	@Override
	public int insertJoinByAccount(Map<String, Object> param,
			HttpServletRequest request) throws BusinessException {
		log.info("新增join和Account....");
		 int u = 0; 
	      try {
			 MapUtils.doing(param, "consumerNo");
			 String consumerNo = param.get("consumerNo")+"";
			 log.info("商户编号:[{}]",consumerNo);		 
			 log.info("开始新增加入....");
			 Map<String, Object> paramMap = new HashMap<String, Object>();
			 int overdate = Integer.parseInt(param.get("mutualUnder").toString()) ;
			 log.info("会员观察天数:[{}]",overdate);
			 String date = DateUtils.getCurrentDateTime2();//当前时间
			 String Format = DateUtils.DATETIME_FORMAT_4;//时间格式
			 String vipdate =DateUtils.dateAddDay(date, Format, overdate);//日期相加
			 log.info("会员观察结束日期:[{}]",vipdate);
			
				 paramMap.put("muId", param.get("mutualId"));//用户ID
				 paramMap.put("consumerNo", consumerNo);//用户编号
				 paramMap.put("eventNubmer", "SJ"+System.currentTimeMillis() + "");//事件编号
				 paramMap.put("joinName", param.get("joinName"));//申请人姓名
				 paramMap.put("joinCard", param.get("joinCard"));//申请人卡号
				 paramMap.put("joinAmt", param.get("payAmt"));//扣除的金额
				 paramMap.put("joinVipzg", param.get("mutualVipzg"));//会员资格
				 paramMap.put("joinTime", date);//加入时间
				 paramMap.put("joinVipsuTime", vipdate);//观察结束时间
				 paramMap.put("joinEffetime", param.get("mutualUnder"));//观察结束天数
				 paramMap.put("joinMutype", param.get("muType"));
				 paramMap.put("agentId", param.get("agentId"));
				 paramMap.put("orderType", param.get("orderType"));
				 log.info("新增加入参数:[{}]",paramMap);
				 u = joinService.addJoin(paramMap);
			 if (u==0) {
				 throw new Exception("帐户添加异常");
			 }	
			} catch (Exception e) {
				log.warn("新增加入信息失败",e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
			}
		return 0 ;
	}

}
