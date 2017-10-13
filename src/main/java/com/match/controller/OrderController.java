package com.match.controller;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.match.service.DBAccountInfService;
import com.match.service.DBAccountService;
import com.match.service.DBOrderService;
import com.util.DateUtils;
import com.util.RequestUtil;
import com.util.bean.Result;


@Controller
@RequestMapping("/ht/order/")
public class OrderController {
	private final static Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private DBOrderService dbOrderService;
	@Autowired
	private DBAccountInfService dbAccountInfServie;
	
	
	
	/**
	 * 交易记录跳转页面
	 */
	private final static String ORDERLIST = "ht/order/orderList";
	
	
	@RequestMapping(value="orderList")
	public String getJoinList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMapByPage(request);
		log.info("[互助orderList] 获得参数:[{}]",param);
		try {
			List<Map<String, Object>> lists = dbOrderService.getOrderList(param);
			log.info("[互助项目交易：list] 项目列表[{}],项目条数[{}]",lists,lists.size());
			RequestUtil.refreshPageParam(request, lists.size());
			request.setAttribute("lists", lists);
			request.setAttribute("listsize", lists.size());
		} catch (Exception e) {
			log.warn("异常错误,错误编码[{}]",e);
		}
		return ORDERLIST;
	}
	
	@RequestMapping(value="orderAdd",method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insert(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("userId", "1");
		session.setAttribute("consumerNo", "100001");
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取前台参数:[{}]",param);
		Map<String, Object> reMap = new HashMap<String, Object>();
		Result result = new Result();
		//userId
		reMap.put("consumerNo", session.getAttribute("consumerNo"));
		log.info("根据商户号查询日志订单:[{}]");
		Map<String, Object> findMap = dbAccountInfServie.findByQuery(reMap);		
		log.info("查询订单参数:[{}]",findMap);
		double su=0;
		double resu=0;
		if (findMap!=null) {
			su = Double.parseDouble(findMap.get("acBal").toString());	
		}
		try {
			resu = Double.parseDouble(param.get("requestAmt").toString());		
			reMap.put("userId", session.getAttribute("userId"));
			reMap.put("orderNo", System.currentTimeMillis() + "");	//订单编号	
			reMap.put("requestAmt", param.get("requestAmt"));//请求金额
			reMap.put("requestTime",DateUtils.getCurrentDateTime2());//请求时间
			reMap.put("orderStatus", param.get("orderStatus")); //交易状态
			reMap.put("payType", param.get("payType")); //交易类型
			reMap.put("payTime", DateUtils.getCurrentDateTime2());//交易时间,获取的当前时间
			reMap.put("payAmt", param.get("requestAmt"));
			reMap.put("orderType", param.get("orderType"));//findMap.get("acBal")
			reMap.put("changeType", "0001");
			reMap.put("acBalcz", param.get("requestAmt")); //操作金额
			reMap.put("oldAcBal", su); //根据商户编号查询到的原资金总额		
			reMap.put("acBal", su+resu); //现在资金总额	
			
			
			log.info("所有添加的参数:[{}]",reMap);
			int u = dbAccountInfServie.addAccountHis(reMap);
			if (u==1) {
				result.setCode("200");
				result.setMsg("success");
			}else{
				 throw new RuntimeException("账户变更历史添加失败");
			}
		} catch (Exception e) {
			result.setCode("201");
			result.setMsg("errorException");
			 throw new RuntimeException("账户变更历史添加异常",e);
		}
		RequestUtil.response(response, result); 
		
	}
	


}
