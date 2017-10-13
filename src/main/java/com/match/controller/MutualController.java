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
import org.springframework.web.bind.annotation.RequestMethod;

import com.match.service.DBMutualService;
import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;
import com.util.RequestUtil;
import com.util.bean.Result;


@Controller
@RequestMapping("/ht/mutual/")
public class MutualController {
	private final static Logger log = LoggerFactory.getLogger(MutualController.class);
	
	/**
	 * 【】跳转页面
	 */
	private final static String mutuallist = "ht/mutual/mutual";
	/**
	 * 跳转项目编辑页面
	 */
	private final static String MUTUALEDIT = "ht/mutual/mutualEdit";
	/**
	 * 跳转新增页面
	 */
	private final static String MUTUALADD = "ht/mutual/mutualInsert";
		
	@Autowired
	private DBMutualService dbMutualService;
	
	
	
	@RequestMapping(value="mutualList")
	public String getMutualList(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMapByPage(request);
		log.info("[互助mutualList] 获得参数:[{}]",param);
		try {
			List<Map<String, Object>> lists = dbMutualService.getMutualList(param);
			log.info("[互助项目list] 项目列表[{}],项目条数[{}]",lists,lists.size());
			RequestUtil.refreshPageParam(request, lists.size());
			request.setAttribute("lists", lists);
			request.setAttribute("listsize", lists.size());
		} catch (Exception e) {
			log.warn("异常错误,错误编码[{}]",e);
		}
		return mutuallist;
	}
	
	@RequestMapping(value="mutualEdit")
	public String mutualEdit(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("获取编辑的参数:[{}]",param);
		try {
			Map<String, Object> muMap = dbMutualService.findMutual(param);
			log.info("根据ID查询项目:[{}]",muMap);
			request.setAttribute("muMap", muMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MUTUALEDIT;
	}
	@RequestMapping(value="updateMutual",method=RequestMethod.POST)
	public void updateMutual(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("编辑项目获得参数:[{}]",param);
		Result result = new Result();
		try {
			int u = dbMutualService.updateMutualHt(param);
			log.info("执行修改项目:[{}]",u);
			if (u==0) {
				throw new Exception("修改项目异常");
			}
			result.setCode("200");
			result.setMsg("success");
		} catch (Exception e) {
			log.warn("修改项目异常:[{}]",e);
			result.setCode("202");
			result.setMsg("errorException");
			e.printStackTrace();
		}
		RequestUtil.response(response, result); 
	}
	
	@RequestMapping(value="mutualInsert")
	public String mutualAdd(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("转到新增页面参数:[{}]",param);
		request.setAttribute("param", param);
		return MUTUALADD;
	}
	
	@RequestMapping(value="insertMutual",method=RequestMethod.POST)
	public void mutualInsert(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> param = RequestUtil.getReqMap(request);
		log.info("新建项目获得参数:[{}]",param);
		Result result = new Result();
		try {
			int u = dbMutualService.insertMutualHt(param);
			log.info("新增项目是否成功:[{}]",u);
			if (u==0) {
				throw new Exception("新增项目异常");
			}
			result.setCode("200");
			result.setMsg("success");
		} catch (Exception e) {
			log.warn("新增项目异常:[{}]",e);
			result.setCode("202");
			result.setMsg("errorException");
			e.printStackTrace();
		}
		RequestUtil.response(response, result); 
	}
	

}
