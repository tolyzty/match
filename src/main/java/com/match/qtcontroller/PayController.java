package com.match.qtcontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.match.qtservice.AccountService;
import com.match.qtservice.OrderService;
import com.match.qtservice.UserService;
import com.match.service.DBAccountService;
import com.match.service.DBBUserService;
import com.match.service.DBOrderService;
import com.util.DateUtils;
import com.util.RequestUtil;
import com.util.constants.Constants;


@Controller
@RequestMapping("/pay/")
public class PayController {

	private final static Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private DBOrderService dbOrderService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DBAccountService dbAccountService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private DBBUserService dbbUserService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "pay/payCallBack", method = RequestMethod.POST)
	public void payCallBack(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = RequestUtil.getReqMap(request);//ParamUtils.getAllParameter(request);
		//String islockId = session.getAttribute("islockId") +"";//推荐人编号
		//String islock = session.getAttribute("islock")+"";//是否为推荐人
		//log.info("根据被推荐人ID查询推荐人信息,修改推荐金额:[{}],[{}]",islockId,islock);
		log.info("[支付]支付回调:" + paramMap);
		
		if (paramMap==null) {
			throw new Exception("接收wx参数空");
		}
		try {
			Map<String, Object> orMap = new HashMap<String, Object>();
			if (("1").equals(paramMap.get("orderStatus"))) {
				log.info("支付成功");
				userService.queryAndUpdateUser(paramMap, request);
				
				orMap.clear();
				orMap.put("orderNo", paramMap.get("orderNo"));
				orMap.put("agentId", paramMap.get("agentId"));
				log.info("获取查询条件参数:[{}]",orMap);
				Map<String,Object> resMap = dbOrderService.findByOrder(orMap);
				log.info("根据订单号,用户编号获取记录:[{}]",resMap);
				if (resMap==null) {
					throw new Exception("订单不存在");
				}
				//resMap.get("orderType");
				orMap.put("orderId", resMap.get("orderId"));
				orMap.put("payOrderNo","888888888888");
				orMap.put("orderStatus", Constants.PAY_ORDER_STATUS_SUC);
				orMap.put("successTime", DateUtils.getCurrentDateTime2());//设置完成时间
				orMap.put("mutualType", resMap.get("muType"));
				orMap.put("orderType",resMap.get("orderType"));
				orMap.put("payAmt", resMap.get("payAmt"));
				orMap.put("consumerNo", resMap.get("consumerNo"));
				log.info("编辑订单所有参数：[{}]",orMap);
				int u = orderService.updateOrderByJoin(request, orMap);
				log.info("回调修改订单成功:[{}]",u);
				if (u==0) {
					throw new Exception("回调修改订单失败");
				}
				int acut = accountService.updateAccAndAccInf(resMap, request);
				log.info("回调成功修改帐户和修改项目:[{}]",acut);
				if (acut==0) {
					throw new Exception("修改帐户,余额记录失败");
				}
			}
		} catch (Exception e) {
			log.warn("接收回调参数失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
	
		
	}
}
