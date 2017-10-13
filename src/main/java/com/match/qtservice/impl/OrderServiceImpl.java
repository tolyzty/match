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

import com.match.qtservice.JoinService;
import com.match.qtservice.OrderService;
import com.match.service.DBAccountInfService;
import com.match.service.DBAccountService;
import com.match.service.DBJoinService;
import com.match.service.DBOrderService;
import com.util.DateUtils;
import com.util.MapUtils;
import com.util.constants.Constants;
import com.util.exception.BusinessException;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private DBAccountService dbAccountService;
	@Autowired
	private DBAccountInfService dbAccountInfService;
	@Autowired
	private DBOrderService dbOrderService;
	@Autowired
	private DBJoinService dbJoinService;

	@Override
	public int updateOrderByAccount(HttpServletRequest request,
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 执行交易保存,和余额变动保存
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class)
	public int insertOrderByJoin(HttpServletRequest request,
			Map<String, Object> param) throws BusinessException,Exception {
		log.info("开始执行新增Order和Join");
		log.info("获得所有参数:[{}]",param);
		log.info("本次操作是：new:新用户,old:老用户充值:[{}]",param.get("orderType"));
		Map<String, Object> accMap = new HashMap<String, Object>();	
		accMap.put("agentId", param.get("agentId"));
		accMap.put("consumerNo", param.get("consumerNo"));
		//Map<String, Object> findJoin = dbJoinService.findByJoin(accMap);
		log.info("根据用户编号和用户号查询加入列表");
		accMap.put("muId", param.get("muId"));
		accMap.put("userId", param.get("userId"));
		accMap.put("orderNo", param.get("orderNo"));	
		accMap.put("requestAmt", param.get("payAmt"));//请求金额
		accMap.put("requestTime",DateUtils.getCurrentDateTime2());//请求时间
		accMap.put("payType", param.get("payType")); //交易类型
		accMap.put("payTime", DateUtils.getCurrentDateTime2());//交易时间,获取的当前时间
		accMap.put("payAmt", param.get("payAmt"));
		accMap.put("orderDesc", param.get("orderDesc"));
		accMap.put("muType", param.get("muType"));//项目类别
		accMap.put("changeType", param.get("changeType"));//支付类型
		accMap.put("orderType", param.get("orderType"));
		accMap.put("orderStatus", param.get("orderStatus"));
		log.info("新增order参数:[{}]",accMap);
		int u = 0;
		try {
			u = dbOrderService.inserOrder(accMap); //执行新增order
			if (u==0) {
				throw new BusinessException("保存订单失败");
		    }
			if (param.get("orderType").equals("old")) {//开始执行用户充值操作,保存order成功,修改join表
				log.info("已有帐户只保存order信息");
				log.info("执行修改join表操作");
				Map<String, Object> upMap = new HashMap<String, Object>();
				/*if (findJoin==null) {
					throw new BusinessException("修改join表失败,查询用户没有加入记录");
				}*/
				//double joAmt=Double.parseDouble(findJoin.get("joinAmt").toString());
				//log.info("原加入表中的金额：[{}]",joAmt);
				//double hjoAmt = Double.parseDouble(param.get("payAmt").toString());
				//log.info("本次充值的金额:[{}]",hjoAmt);
				//double jAmt = joAmt + hjoAmt;
				//log.info("修改后的金额:[{}]",jAmt);
				upMap.put("joinAmt", param.get("payType"));
				upMap.put("agentId", param.get("agentId"));
				upMap.put("consumerNo", param.get("consumerNo"));
				log.info("修改的全部参数:[{}]",upMap);
				int oldu = dbJoinService.updateJoinByAmt(upMap);
				if (oldu==0) {
					throw new BusinessException("修改join记录异常");
				}
				return oldu;
			}
			 	
			 log.info("开始新增Join表信息...");
			 MapUtils.doing(param, "consumerNo");
			 String consumerNo = param.get("consumerNo")+"";
			 log.info("用户编号:[{}]",consumerNo);		 
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
				 u = dbJoinService.addJoin(paramMap); //执行新增join
			 if (u==0) {
				 throw new BusinessException("帐户添加异常");
			 }	
		} catch (BusinessException e) {
			log.warn("执行保存异常:[{}]",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public int updateOrderByJoin(HttpServletRequest request,
			Map<String, Object> param) throws BusinessException {
		log.info("充值成功,修改order和Join状态");
		Map<String, Object> ojMap = new HashMap<String, Object>();
		ojMap.put("orderStatus", Constants.PAY_ORDER_STATUS_SUC);
		ojMap.put("agentId", param.get("agentId"));
		ojMap.put("orderNo", param.get("orderNo"));
		ojMap.put("orderId", param.get("orderId"));
		ojMap.put("payOrderNo", param.get("payOrderNo"));
		ojMap.put("successTime", param.get("successTime"));
		int ou = 0;
		try {
			if (param.get("orderType").equals("new")) {
				ou = dbOrderService.updateJoinByOrder(ojMap);
				log.info("修改成功");
			}else{
				ojMap.put("joinAmt", param.get("payAmt"));
				ojMap.put("consumerNo", param.get("consumerNo"));
				log.info("join表修改:[{}]",ojMap);
			   	ou=dbJoinService.updateJoinByAmt(ojMap);
			}
			if (ou==0) {
				throw new Exception("修改状态失败");
			}	
		} catch (Exception e) {
			log.warn("修改order状态失败");
			e.printStackTrace();
		}
		return ou;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int insertOrderByAccountInf(HttpServletRequest request,
			Map<String, Object> param) throws BusinessException, Exception {
			log.info("已有账户充值操作:[{}]",param);
			Map<String, Object> accMap = new HashMap<String, Object>();
			accMap.put("muId", param.get("muId"));
			accMap.put("userId", param.get("userId"));
			accMap.put("agentId", param.get("agentId"));
			accMap.put("orderNo", param.get("orderNo"));
			accMap.put("consumerNo", param.get("consumerNo"));
			accMap.put("requestAmt", param.get("payAmt"));//请求金额
			accMap.put("requestTime",DateUtils.getCurrentDateTime2());//请求时间
			accMap.put("payType", param.get("payType")); //交易类型
			accMap.put("payTime", DateUtils.getCurrentDateTime2());//交易时间,获取的当前时间
			accMap.put("payAmt", param.get("payAmt"));
			accMap.put("orderDesc", param.get("orderDesc"));
			accMap.put("muType", param.get("muType"));//项目类别
			accMap.put("changeType", param.get("changeType"));//支付类型
			accMap.put("orderType", param.get("orderType"));
			accMap.put("orderStatus", param.get("orderStatus"));
			log.info("余额充值新增order:[{}]",accMap);
			int u = 0;
			int uacinf=0;
			try {
				u = dbOrderService.inserOrder(accMap); //执行新增order
			if (u==0) {
				throw new Exception("保存订单失败");
			}
			Map<String, Object> accInfMap = new HashMap<String, Object>();
			accInfMap.put("agentId", param.get("agentId"));
			Map<String, Object> acMap =  dbAccountInfService.findByQuery(accInfMap);
			log.info("根据用户编号查找余额表最后一条记录:[{}]",acMap);
			double oldAcBal = Double.parseDouble(acMap.get("acBal").toString());
			double oldFrozBalance = 0;
			double AcBal = Double.parseDouble(param.get("payAmt").toString());
			log.info("原帐户余额:[{}],交易金额:[{}],更新后帐户余额：[{}]",oldAcBal,AcBal,oldAcBal+AcBal);
			accInfMap.put("consumerNo", param.get("consumerNo"));
			accInfMap.put("accountNo", param.get("consumerNo")+param.get("muType").toString());
			accInfMap.put("muType", param.get("muType"));
			accInfMap.put("acBal", AcBal+oldAcBal);
			accInfMap.put("lstTxDatetime", DateUtils.getCurrentDateTime2());
			accInfMap.put("orderStatus", param.get("orderStatus"));			
			accInfMap.put("oldAcBal", oldAcBal);
			accInfMap.put("acBalcz", param.get("payAmt"));
			accInfMap.put("oldFrozBalance", oldFrozBalance);
			accInfMap.put("changeType", param.get("changeType"));
			//parMap.put("accOrderNo", param.get("payOrderNo"));
			accInfMap.put("changeDesc", param.get("orderDesc"));
			accInfMap.put("logDateTime", DateUtils.getCurrentDateTime2());
			log.info("执行保存[accountInf]参数:[{}]",accInfMap);
		    uacinf = dbAccountInfService.inserAccountInf(accInfMap);
			//uacinf = 1;
			if (uacinf==0) {
				throw new Exception("保存余额变动信息异常");
			}
			
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				e.printStackTrace();
			}
		return u;
	}

}
