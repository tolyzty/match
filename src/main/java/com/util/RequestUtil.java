package com.util;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.constants.Constants;




public class RequestUtil {
	private static Logger log = LoggerFactory.getLogger(RequestUtil.class);
	private static String charset = "UTF-8";
	
	public static void response(HttpServletResponse response, String resStr) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		OutputStream out = response.getOutputStream();
		out.write(resStr.getBytes(charset));
		out.flush();
		out.close();
	}
	
	public static void response(HttpServletResponse response, Map<String,Object> paramMap) throws Exception {
		response(response, JUtil.toJsonString(paramMap));
	}
	
	public static void response(HttpServletResponse response, Object obj) throws Exception {
		response(response, JUtil.jsonStrFromObject(obj));
	}

	public static String getServletName(HttpServletRequest request) {
		String url = request.getRequestURI();
		try {
			url = url.substring(url.lastIndexOf("/") + 1);
		} catch (Exception e) {
			log.error("获取请求名称失败:" + e);
		}
		return url;
	}

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了合作商软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向合作商的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	String REQUESTED_AJAX = "XMLHttpRequest";
	String HEADER_REQUESTED = "X-Requested-With";


	public static Map<String, Object> getReqMap(HttpServletRequest req) {
		return getReqMap(req, null);
	}

	public static Map<String, Object> getReqMap(HttpServletRequest req, Map<String, Object> map) {
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
		}

		Map paramMap = req.getParameterMap();
		if ((paramMap == null) || (paramMap.isEmpty())) {
			return map;
		}

		Set<String> keySet = paramMap.keySet();
		for (String key : keySet) {
			String[] values = (String[]) paramMap.get(key);
			if ((values == null) || (values.length == 0))
				map.put(key, "");
			else if (values.length == 1)
				map.put(key, values[0]);
			else {
				map.put(key, values);
			}
		}
		return map;
	}
	
	public static Map<String, Object> getReqMapByPage(HttpServletRequest req) {
		return getReqMapByPage(req,null);
	}
	public static Map<String, Object> getReqMapByPage(HttpServletRequest req, Map<String, Object> map) {
		Map<String,Object> reqMap = getReqMap(req, map);
		Map<String, Object> pageMap = new LinkedHashMap<String,Object>();
		//每页显示的最大记录数
		Integer pageSize = ObjectUtil.getInt(reqMap.get("pageSize"));
		if(pageSize <= 0){
			pageSize = 10;
		}
		// 当前页码
		Integer pageIndex = ObjectUtil.getInt(reqMap.get("pageIndex"));
		if(pageIndex <= 0){
			pageIndex = 1;
		}
		
		//开始位置
		Integer startPage = (pageIndex -1) * pageSize;
		
		pageMap.put("pageSize", pageSize);
		pageMap.put("pageIndex", pageIndex);
		pageMap.put("startPage", startPage); 
		
//		req.setAttribute("page", pageMap);
		
		reqMap.putAll(pageMap);
		req.setAttribute("reqMap", reqMap);
		
		return reqMap;
	}
	
	public static void refreshPageParam(HttpServletRequest req,int count){
		refreshPageParam(req,(long)count);
	}
	@SuppressWarnings("unchecked")
	public static void refreshPageParam(HttpServletRequest req,long count){
		Map<String,Object> reqMap = (Map<String,Object>)req.getAttribute("reqMap");
		//每页显示的最大记录数
		Integer pageSize = ObjectUtil.getInt(reqMap.get("pageSize"));
		if(pageSize <= 0){
			pageSize = 10;
		}
		// 当前页码
		Integer pageIndex = ObjectUtil.getInt(reqMap.get("pageIndex"));
		if(pageIndex <= 0){
			pageIndex = 1;
		}
		
		if(count <= 0){
			count = 0;
		}
		
		long totalPage = count / pageSize;
		if(count % pageSize > 0){
			totalPage ++;
		}
		
		reqMap.put("pageSize", pageSize);
		reqMap.put("pageIndex", pageIndex );
		reqMap.put("totalPage", totalPage);
		req.setAttribute("totalCount", count);
	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader(this.HEADER_REQUESTED);

		return this.REQUESTED_AJAX.equalsIgnoreCase(requestType);
	}

	/**
	 * 获得请求内容
	 * 
	 * @return
	 */
	public static String getQueryBody(HttpServletRequest request) {
		try {
			BufferedReader br = request.getReader();

			String str, reqBody = "";
			while ((str = br.readLine()) != null) {
				reqBody += str;
			}
			return reqBody.trim();
		} catch (Exception e) {
			return "";
		}
	}
	

	
	/**
	 * 获得简单的json响应结果(即将Map添加上签名以后直接转为json,不进行base64编码等操作,签名密钥从request的Attribute中取得)
	 * 
	 * @param request
	 * @param resMap
	 * @return
	 */
	public static String getSimpleRspJson(HttpServletRequest request, Map<String, Object> resMap) {
		String consumerNo = (String) request.getAttribute("consumerNo");
		if (StringUtils.isNotEmpty(consumerNo) && resMap.get("consumerNo") == null) {
			log.debug("添加商户编号consumerNo[{}]到返回集", consumerNo);
			resMap.put("consumerNo", consumerNo);
		}
		String signKey = (String) request.getAttribute("signKey");
		String signStr = MapUtils.getSignStrByTreeMap(resMap, true);
		log.info("返回加签元数据:[{}]",signStr);
		String signValue = MD5.encryption(signStr + "&key=" + signKey).toUpperCase();
		log.info("签名结果[{}]",signValue);
		resMap.put(Constants.API_SIGN_NAME, signValue);
		return JUtil.toJsonString(resMap);
	}
	
	/**
	 * 获取当前系统的ip地址
	 * 
	 * @return
	 */
	public static String getSystemIp() {
		// 根据网卡取本机配置的IP
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {

				NetworkInterface ni = netInterfaces.nextElement();
//				System.out.println("DisplayName:" + ni.getDisplayName());
//				System.out.println("Name:" + ni.getName());
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					String ip = ips.nextElement().getHostAddress();
					if(ip != null && !ip.equals("127.0.0.1") && ip.indexOf(".") != -1 && ip.indexOf(":") == -1){
						return ip;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
