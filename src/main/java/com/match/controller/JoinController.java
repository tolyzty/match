package com.match.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.bean.Result;
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

	/**
	 * [JOIN编辑页面]跳转
	 */
	private final static String JOINEDIT = "ht/join/joinEdit";

	/**
	 * [404]页面
	 */
	private final static String JOINERROR = "error/404";
	
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

	@RequestMapping(value="joinEdit")
	public String JoinEdit(HttpServletRequest request,HttpServletResponse response){
		log.info("进入编辑页面..");
		Map<String, Object> param = RequestUtil.getReqMap(request);
		try {
			log.info("获取编辑参数:[{}]",param);
			Map<String,Object> findMap = dbJoinService.findByJoin(param);
			log.info("根据ID查询修改信息:[{}]",findMap);
			if (findMap==null){
				return JOINERROR;
			}else{
				request.setAttribute("findMap",findMap);
				return JOINEDIT;
			}
		}catch (Exception e){
			log.info("异常:[{}]",e);
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "joinUpdate",method = RequestMethod.POST)
	public void joinUpdate(HttpServletResponse response,HttpServletRequest request) throws Exception {
	    log.info("执行编辑操作");
	    Result result = new Result();
        Map<String, Object> param = RequestUtil.getReqMap(request);
	    try{
	       int upMap = dbJoinService.updateJoin(param);
	       log.info("编辑操作结果:[{}]",upMap);
	       if (upMap==0){
	           new Exception("修改失败");
           }
           result.setCode("200");
	       result.setMsg("success");

        }catch (Exception e){
            result.setCode("202");
            result.setMsg("error");
            log.info("编辑操作异常编码:[{}]",e);
            e.printStackTrace();
        }
        RequestUtil.response(response, result);
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
