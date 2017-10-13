package com.match.qtcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPBinding;

import org.apache.commons.httpclient.Header;
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
import com.match.qtservice.JoinService;
import com.match.qtservice.MutualService;
import com.match.qtservice.OrderService;
import com.match.service.DBAccountInfService;
import com.match.service.DBAccountService;
import com.match.service.DBJoinService;
import com.match.service.DBMutualService;
import com.match.service.DBOrderService;
import com.util.DateUtils;
import com.util.MapUtils;
import com.util.RequestUtil;
import com.util.bean.Result;
import com.util.constants.Constants;


@Controller
@RequestMapping("/index/")
public class IndexController {
	private final static Logger log = LoggerFactory.getLogger(IndexController.class);
	
	/**
	 * 首页
	 */
	private final static String INDEX = "index/mutual";
	/**
	 * 跳转登录页面
	 */
	private final static String LOGIN = "user/login";
	/**
	 * 跳转支付页面
	 */
	private final static String SENDPAY = "index/sendpay";
	/**
	 * 帐户未付款
	 */
	private final static String SENDPAYS = "index/sendpays";
	/**
	 * 跳转到项目详情页面
	 */
	private final static String MUTUAL = "index/mutual";
	/**
	 * 加入项目页面
	 */
	private final static String JOINMUTUAL = "index/joinmutual";
	/**
	 * 充值页面
	 */
	private final static String WXPAY = "index/wxpay";
	/**
	 * 充值成功后页面
	 */
	private final static String PAYSUCCESS = "index/paySuccess";
	
	
	@Autowired
	private DBMutualService dbMutualService;	
	@Autowired
	private DBJoinService dbJoinService;
	@Autowired
	private DBOrderService dbOrderService;
	@Autowired
	private JoinService joinService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AccountService accountService;
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		log.info("进入首页Controller");
		
		return INDEX;
	}
	
	@RequestMapping(value="sendpays")
	public String indexpay(HttpServletRequest request,HttpServletResponse response){
		log.info("未加入的帐户,执行付款操作");
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取未加入JoinId参数:[{}]",param);
		try {
		  Map<String, Object> joMap = dbOrderService.queryByOrderAndJoin(param);
		  log.info("根据ID查找的参数:[{}]",joMap);
		  log.info("前台数据:[{}]",joMap);
		  request.setAttribute("params", joMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SENDPAYS;
	}
	
	
	@RequestMapping(value="mutual")
	public String getMutualById(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		/*HttpSession session = request.getSession();
		Map<String, Object> reMap = new HashMap<String, Object>();
		log.info("session值：[{}]",session.getAttribute("userPhone"));
		if (session.getAttribute("userPhone")==null) {
			return LOGIN;
		}
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取参数:[{}],获取的ID：[{}]",param,param.get("mutualId"));
		Map<String, Object> muMap = dbMutualService.findMutual(param);
		log.info("根据ID获取项目信息:[{}]",muMap);
		log.info("预存金额:[{}]",muMap.get("mutualPreamt"));	*/
		try {
			//param.put("consumerNo", session.getAttribute("consumerNo"));//商户编号
			//Map<String, Object> paramMap = dbAccountService.findByQueryAmt(param);//根据商户编号查询
			/*log.info("获取该账户的余额参数:[{}]",paramMap);
			if (ObjectUtil.getDouble(paramMap.get("acBal"))<ObjectUtil.getDouble(paramMap.get("mutualPreamt"))) {
				log.info("帐户金额：[{}],预存金额:[{}]",paramMap.get("acBal"),paramMap.get("mutualPreamt"));
				return PAY;
			}
			param.clear();*/
			List<Map<String, Object>> lists = dbMutualService.getMutualList(param);
			log.info("项目列表:[{}]",lists);
			request.setAttribute("lists", lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MUTUAL;
	}
	
	
	@RequestMapping(value="joinMutual",method=RequestMethod.POST)
	public String findByMutualId(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		log.info("session值：[{}]",session.getAttribute("userPhone"));
		log.info("consumerNo的值：[{}]",session.getAttribute("consumerNo"));
		if (session.getAttribute("userPhone")==null) {
			return LOGIN;
		}
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("根据ID获取项目信息参数:[{}]",param);
		String muId = param.get("mutualId").toString();//获取项目ID
		/*String muPreamt = param.get("mutualPreamt").toString();//预存金额
		log.info("项目Id :[{}]",muId);
		param.put("consumerNo", session.getAttribute("consumerNo"));//商户编号
		log.info("所有参数:[{}]",param);
		Map<String, Object> paramMaps = dbAccountService.findByQueryAmt(param);//根据商户编号查询
		log.info("获取该账户的余额参数:[{}]",paramMaps);*/
		/*if (paramMaps==null) {
			log.info("帐户空,去支付页面");
			return "redirect:/index/pay.do";
		}else if(ObjectUtil.getDouble(paramMaps.get("acBal"))<ObjectUtil.getDouble(muPreamt)) {
			log.info("查询帐户余额不足");
			return "redirect:/index/pay.do";
		}*/
		log.info("查询余额成功");
		//log.info("帐户金额：[{}],预存金额:[{}]",paramMaps.get("acBal"),muPreamt);
		return "redirect:/index/joinmutual.do?mutualId="+muId+"";
	}
	
	@RequestMapping(value="joinmutual")
	public String joinMutual(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		if (session.getAttribute("userPhone")==null) {
			return LOGIN;
		}
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("根据ID获取项目信息参数:[{}]",param);
		if (param.get("mutualId")!=null) {
			String muId = param.get("mutualId").toString();//获取项目ID
			param.clear();
			param.put("mutualId", muId);//根据ID查询
		}else{
			param.put("mutualType", param.get("mutualType"));//根据项目类型
		}	
		try {
			log.info("执行findMutal查询参数：[{}]",param);
			Map<String, Object> paramMap = dbMutualService.findMutual(param);
			log.info("根据ID获取项目:[{}]",paramMap);		
			request.setAttribute("paramMap", paramMap);
		} catch (Exception e) {
			log.warn("非法帐户登录,请重新登录");
		}
		return JOINMUTUAL;
	}
	
	/**
	 * 执行充值操作
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="pay",method=RequestMethod.POST)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String addMutual(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Map<String, Object> param = RequestUtil.getReqMap(request);
		String paragentId = param.get("agentId")+"";
		Map<String, Object> reMap = new HashMap<String, Object>();
		log.info("获取的参数：[{}]",param);
		String consumerNo = session.getAttribute("consumerNo") +"";//用户编号
		String agentId = session.getAttribute("agentId") +"";//推荐ID
		String muId = param.get("mutualId")+"";//项目ID
		log.info("用户编号:[{}],成员编号:[{}],项目编号:[{}]",consumerNo,agentId,muId);
		log.info("开始新增[order]充值记录....");				
		String orderNo = System.currentTimeMillis() + "";//订单编号
		String orderDesc = "";
		 Map<String, Object> parMap = new HashMap<String, Object>();
		 parMap.put("consumerNo", consumerNo);
		 Map<String, Object> orMap = dbOrderService.orderByLimit(parMap);
		 log.info("根据用户编号获取的交易表查询信息:[{}]",orMap);
		 Map<String, Object> ageMap = dbJoinService.queryByLimit(parMap); 
		 log.info("根据用户编号获取的加入表查询信息:[{}]",ageMap);
		 param.put("agentId", agentId);//加入用户编号
			if (param.get("orderType").equals("new")) {
				orderDesc = "互动会员充值";
					 if (orMap!=null) {
						log.info("查询是否存在:[{}]",ageMap.get("agentId"));
						Integer us = Integer.parseInt(ageMap.get("agentId").toString());
						param.put("agentId", us+1);
						log.info("获取的US参数是:[{}]",param.get("agentId"));
					  } 
			}else{
				 orderDesc = "帐户余额充值";
				 param.put("agentId", paragentId);//加入用户编号
				log.info("老用户充值:[{}]",param.get("agentId"));
			}	
		
		 log.info("开始新增[join]加入记录....");						
		 log.info("传入的所有参数:[{}],传入的推荐ID:[{}]",param,param.get("agentId"));
		 int u = 0;
		try {
			param.put("muId", muId);
			param.put("userId", session.getAttribute("userId"));
			param.put("agentId", param.get("agentId"));
			param.put("orderNo", orderNo);
			param.put("consumerNo", consumerNo);
			//reMap.put("requestAmt", param.get("payAmt"));//请求金额
			//reMap.put("requestTime",DateUtils.getCurrentDateTime2());//请求时间
			//reMap.put("payType", param.get("payType")); //交易类型
			//reMap.put("payTime", DateUtils.getCurrentDateTime2());//交易时间,获取的当前时间
			//reMap.put("payAmt", param.get("payAmt"));
			param.put("orderDesc", orderDesc);
			//reMap.put("muType", param.get("muType"));//项目类别
			param.put("changeType", Constants.CHL_PAY_TYPE_WEIXIN);//支付类型
			//reMap.put("orderType", param.get("orderType"));
			param.put("orderStatus", Constants.PRD_ORDER_STATUS_IN_HAND);
			if (param.get("orderType").equals("new")) {
				log.info("order表新增和join表新增参数:[{}]",param);
				log.info("开始新增order记录和join参数:[{}]",param);			
				 u = orderService.insertOrderByJoin(request, param);
				if (u==0) {
					throw new Exception("开始记录失败[order],[join]");
				}
				int ut = accountService.insertAccAndAccInf(param, request);
				if (ut==0) {
					throw new Exception("记录帐户失败[account],[accountInf]");
				}
			}else{
			   log.info("已有帐户,用户充值操作:");
			   log.info("新增order和accountInf");
			   u = orderService.insertOrderByAccountInf(request, param);
			   if (u==0) {
				  throw new Exception("已有账户新增失败"); 
			   }
			}
		
			log.info("前台数据:[{}]",param);
			param.put("orderStatus", u);
			request.setAttribute("params", param);
		} catch (Exception e) {
			log.warn("加入项目异常:[{}]",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return SENDPAY;
	}
	

	
	/**
	 * 查询用户是否加入过项目
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="findByJoin",method=RequestMethod.POST)
	public void findByJoin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Result result = new Result();
		Map<String, Object> param = RequestUtil.getReqMap(request);
		param.put("consumerNo", session.getAttribute("consumerNo"));
		log.info("检查加入信息参数:[{}]",param);
		try {
		MapUtils.doing(param, "consumerNo", "muType","joinName","joinCard");
		Map<String, Object> joinMap = new HashMap<String, Object>();
			joinMap.put("consumerNo", param.get("consumerNo"));
			joinMap.put("joinMuType", param.get("muType"));
			joinMap.put("joinName", param.get("joinName"));
			joinMap.put("joinCard", param.get("joinCard"));
			Map<String, Object> joMap = joinService.findByJoin(joinMap);
			log.info("验证用户是否加入过项目:[{}]",joMap);
			if (joMap==null) {
				result.setCode("200");
				result.setMsg("success");
			}else{
				result.setCode("204");
				result.setMsg("joined");
			}
		} catch (Exception e) {
			result.setCode("201");
			result.setMsg("errorException");
			e.printStackTrace();
		}
		RequestUtil.response(response, result); 
	}
	
	@RequestMapping(value="wxpay")
	public String wxPay(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		if (session.getAttribute("userPhone")==null) {
			return LOGIN;
		}
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("根据ID获取项目信息参数:[{}]",param);
		String agentId = param.get("agentId").toString();//获取项目ID
		try {
			param.put("agentId", agentId);	
			log.info("查找join列表参数：[{}]",param);
			Map<String, Object> parMap = dbJoinService.queryByJoinAndMu(param);
			log.info("根据ID获取项目:[{}]",parMap);		
			request.setAttribute("parMap", parMap);
		} catch (Exception e) {
			log.warn("非法帐户登录,请重新登录");
			e.printStackTrace();
		}
		return WXPAY;
	}
	
	@RequestMapping(value="wxpays",method=RequestMethod.POST)
	public String postPay(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		Map<String, Object> param = RequestUtil.getReqMap(request);
		Map<String, Object> reMap = new HashMap<String, Object>();
		log.info("获取所有充值参数:[{}]",param);
		
		log.info("模拟提交支付.....");
		/**
		 * 充值- 
		 * 新增order一条信息 
		 * 修改join 加入金额 = 充值金额+原金额
		 * 
		 * 修改account 总余额 = 充值金额+原余额
		 * 新增 accountInf一条信息
		 * 
		 * 
		 * 修改mutual 已加入项目金额 = 充值金额 + 原项目金额
		 * 
		 */
		log.info("模拟返回支付结果....");
		log.info("success");
		String succ = "success";
		
		if (succ.equals("success")) {
			log.info("支付成功");
			
			/*
			 * 修改order accountInf 
			 * if orderType =="new"{//新用户加入
			 * 		新增account join表
			 * 		修改mutual
			 * }else{ //老用户充值
			 * 	    修改account join表
			 * 	    修改mutual 金额
			 * }
			 * 
			 */
			
		}else{
			
		}
		
		String orderNo = System.currentTimeMillis() + "";//订单编号
		String orderDesc = "";
		if (param.get("orderTyope").equals("new")) {
			orderDesc = "新用户加入";
			//update
		}else{
			orderDesc = "余额不组,充值操作";
			//update
		}
		try {
			log.info("发送充值...");
			reMap.put("userId", session.getAttribute("userId"));
			reMap.put("orderNo", orderNo);
			reMap.put("consumerNo", param.get("consumerNo"));
			reMap.put("requestAmt", param.get("payAmt"));//请求金额
			reMap.put("requestTime",DateUtils.getCurrentDateTime2());//请求时间
			reMap.put("payType", "1"); //交易类型
			reMap.put("payTime", DateUtils.getCurrentDateTime2());//交易时间,获取的当前时间
			reMap.put("payAmt", param.get("payAmt"));
			reMap.put("orderDesc", orderDesc);
			reMap.put("muType", param.get("joinMutype"));//项目类别
			reMap.put("changeType", Constants.CHL_PAY_TYPE_WEIXIN);//支付类型
			reMap.put("agentId", param.get("agentId"));//推荐人ID默认shi
			reMap.put("orderStatus", param.get("orderStatus"));
			log.info("order表新增余额不足充值参数:[{}]",reMap);
			int u = orderService.insertOrderByJoin(request, reMap);
			if (u==0) {
				throw new Exception("新增交易订单失败");
			}
			request.setAttribute("orderStatus", u);
		} catch (Exception e) {
			log.warn("订单,inf提交异常:[{}]",e);
			e.printStackTrace();
		}
		return PAYSUCCESS;
	}
	
}
