package com.match.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.match.service.DBJoinService;
import com.util.RequestUtil;




@Controller
@RequestMapping("/ht/join/")
public class JoinController {
	private final static Logger log = LoggerFactory.getLogger(JoinController.class);
	
	/**
	 * [Join页面]跳转页面
	 */
	private final static String JOINLIST = "ht/join/joinList";
	
	@Autowired
	private DBJoinService dbJoinService;
	
	

	@RequestMapping(value="joinList")
	public String getJoinList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMapByPage(request);
		log.info("[互助joinList] 获得参数:[{}]",param);
		try {
			List<Map<String, Object>> lists = dbJoinService.getJoinList(param);
			log.info("[互助项目加入：list] 项目列表[{}],项目条数[{}]",lists,lists.size());
			int totleconent = dbJoinService.getJoinCount(param);
			RequestUtil.refreshPageParam(request, totleconent);
			log.info("项目总数：[{}]",totleconent);
			request.setAttribute("lists", lists);
			request.setAttribute("listsize", totleconent);
		} catch (Exception e) {
			log.warn("异常错误,错误编码[{}]",e);
		}
		return JOINLIST;
	}
	
	/*@RequestMapping(value="joinUpdate",method=RequestMethod.POST)
	public void updateQueryAmt(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取批量编辑参数:[{}]",param);
		param.put("sqamt", "1.00");
		double sqamt = Double.parseDouble(param.get("sqamt").toString());
		double joinAmt = 0;
		try {
			Map<String, Object> prMap = new HashMap<String, Object>();
			prMap.put("joinMutype", "02");
			prMap.put("sqamt", sqamt);
			log.info("pr:[{}]",prMap);
			//List<Map<String, Object>> lists = dbJoinService.getJoinList(param);
			//log.info("根据项目查询加入:[{}]",lists);//2个人
			//log.info("listsSzie:[{}]",lists.size());//2人  //申请金额：100元
			int u = dbJoinService.updateQueyAmt(prMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
