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

import com.match.service.DBCustService;
import com.util.RequestUtil;


@Controller
@RequestMapping("/ht/cust/")
public class CustController {
	
	private final static Logger log = LoggerFactory.getLogger(CustController.class);
	@Autowired
	private DBCustService dbCustService;
	
	
	
	
	/**
	 * 跳转到cust页面
	 */
	private static final String CUSETLIST = "ht/cust/custList";
	
	@RequestMapping(value="custparam")
	public String custparam(HttpServletRequest request){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取参数Param：[{}]",param);
		request.setAttribute("cust", param);
		return "ht/cust/custparam";
	}
	
	
	@RequestMapping(value="custList")
	public String custList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMapByPage(request);
		log.info("[互助cust] 获得参数:[{}]",param);
		try {
			List<Map<String, Object>> lists = dbCustService.getCustList(param);
			log.info("[互助申请人：list] 项目列表[{}],项目条数[{}]",lists,lists.size());
			int totleconent = dbCustService.getCustCount(param);
			RequestUtil.refreshPageParam(request, totleconent);
			log.info("项目总数：[{}]",totleconent);
			request.setAttribute("lists", lists);
			request.setAttribute("listsize", totleconent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CUSETLIST;
	}

}
