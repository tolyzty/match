package com.match.qtcontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.match.service.DBCustService;
import com.match.service.DBJoinService;
import com.util.DateUtils;
import com.util.RequestUtil;
import com.util.constants.Constants;


@Controller
@RequestMapping("/cust/")
public class qtCustController {
	
	private final static Logger log = LoggerFactory.getLogger(qtCustController.class);
	/**跳转前台互助申请页面**/
	private final static String CUSTADD = "cust/custAdd";
	/**申请互助成功页面**/
	private final static String CUSTSUCCESS = "cust/custSuccess";
	/**申请互助失败页面**/
	private final static String CUSTERROR = "cust/custError";
	
	@Autowired
	private DBJoinService dbJoinService;
	@Autowired
	private DBCustService dbCustService;
	

	
	@RequestMapping(value="custAdd")
	public String custList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> paramMap = RequestUtil.getReqMap(request);
		log.info("获取互助的参数:[{}]",paramMap);
		request.setAttribute("agentId", paramMap.get("agentId"));
		return CUSTADD;
	}
	
	@RequestMapping(value="addcust",method=RequestMethod.POST)
	public String addCust(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> paramMap = RequestUtil.getReqMap(request);
		log.info("新增互助参数:[{}]",paramMap);
		String nubmer ="";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("agentId", paramMap.get("agentId"));
		Map<String, Object> cuMap = dbCustService.queryCust(resMap);
		if (cuMap!=null) {
			request.setAttribute("error", "对不起,您已经申请过互助.");
			return CUSTERROR;
		}
		try {
			Map<String, Object> queMap = dbJoinService.findByJoin(resMap);
			log.info("查询加入信息:[{}]",queMap);
			resMap.clear();
			resMap.put("jId", queMap.get("joinId"));//ID加入表的ID
			resMap.put("agentId", queMap.get("agentId"));//编号
			resMap.put("custName", queMap.get("joinName"));//姓名
			resMap.put("custGender", paramMap.get("custGender"));//性别
			resMap.put("custAddress", paramMap.get("custAddress"));//现居住地
			resMap.put("custAmt", paramMap.get("custAmt"));//申请互助金额
			resMap.put("custTime", DateUtils.getCurrentDateTime2());//申请互助时间
			resMap.put("custMutype", queMap.get("joinMutype"));//项目类型
			resMap.put("custStatus", Constants.CUST_STATUC_AUDIT);//项目类型
			if (queMap.get("joinMutype").equals("02")) {
				nubmer = "QNHZ"+System.currentTimeMillis();
			}
			resMap.put("custNumbers", nubmer);
			log.info("所有新增参数:[{}]",resMap);
			int u = dbCustService.addCust(resMap);
			if (u==0) {
				request.setAttribute("error", "互助申请失败,请联系客服");
				return CUSTERROR;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CUSTSUCCESS;
	}

}
