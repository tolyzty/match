package com.match.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.match.service.DBAccountInfService;
import com.match.service.DBAccountService;
import com.match.service.impl.DBAccountServiceImpl;
import com.util.RequestUtil;


@Controller
@RequestMapping("/ht/account/")
public class AccountController {
	
	private final static Logger log = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private DBAccountService dbAccountService;
	@Autowired
	private DBAccountInfService dbAccountInfServie;
	
	
	
	/**
	 * 跳转到帐户页面
	 */
	private final static String ACCOUNT = "ht/account/accountList";
	/**
	 * 跳转日志管理
	 */
	private final static String LOGS = "ht/account/logsList";
	
	@RequestMapping(value="accountList")
	public String getAccount(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMapByPage(request);
		log.info("[互助帐户List] 获得参数:[{}]",param);
		try {

			List<Map<String, Object>> lists = dbAccountService.getAccountLists(param);
			log.info("[互助帐户：list] 项目列表[{}],项目条数[{}]",lists,lists.size());
			int totleconent = dbAccountService.findCount(param);
			RequestUtil.refreshPageParam(request, totleconent);
			log.info("[互助帐户总数]:[{}]",totleconent);
			request.setAttribute("lists", lists);
			request.setAttribute("listsize", totleconent);
		} catch (Exception e) {
			log.warn("异常错误,错误编码[{}]",e);
		}
		return ACCOUNT;
	}
	
	@RequestMapping(value="logsList")
	public String getlog(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMapByPage(request);
		log.info("[日志管理Log] 查询参数:[{}]",param);
		try {
			List<Map<String, Object>> lists = dbAccountInfServie.getAccountList(param);
			log.info("[互助帐户：list] 项目列表[{}],项目条数[{}]",lists,lists.size());
			int count = dbAccountInfServie.findCountInf(param);
			RequestUtil.refreshPageParam(request, count);
			request.setAttribute("lists", lists);
			request.setAttribute("listsize", count);
		} catch (Exception e) {
			log.warn("异常错误,错误编码[{}]",e);
		}
		return LOGS;
	}
	
}
