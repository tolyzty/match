package com.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.exception.BusinessException;
import com.util.exception.ExcepCode;

public class MapUtils extends org.apache.commons.collections.MapUtils{
	private static Logger log = LoggerFactory.getLogger(MapUtils.class);
	/**
	 * 按参数名称排序获取key=value&key=value
	 * @param paramMap 参数map
	 * @param ignoreEmpty 是否忽略掉为空的参数
	 * @return
	 */
	public static String getSignStrByTreeMap(Map<String,? extends Object> param,boolean ignoreEmpty){
		Map<String,Object> paramMap = new TreeMap<String, Object>();
		paramMap.putAll(param);
		
		return getSignStrByMap(paramMap,ignoreEmpty);
		
	}

	/**
	 * key=value&key=value
	 * @param paramMap 参数map
	 * @param ignoreEmpty 是否忽略掉为空的参数
	 * @return
	 */
	public static String getSignStrByMap(Map<String,? extends Object> param,boolean ignoreEmpty){
		
		if(param == null  || param.isEmpty()){
			return "";
		}
		StringBuffer str = new StringBuffer("");
		int i = 0;
		Object obj = null;
		for (String key : param.keySet()) {
			obj = param.get(key);
			//忽略空值
			if(ignoreEmpty){
				if(obj == null || StringUtils.isEmpty(obj.toString())){
					continue;
				}
			}
			
			if(i > 0){
				str.append("&");
			}
			str.append(key).append("=").append(param.get(key));
			i++;
		}
		
		return str.toString();
		
	}
	
	/**
	 * url参数格式字符串切割到Map
	 * @param str
	 * @return
	 */
	public static Map<String,Object> urlParamsToMap(String str){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		if(StringUtils.isEmpty(str)){
			return map;
		}
		String[] params = str.split("&");
		for (String param : params) {
			int idx = param.indexOf("=");
			map.put(param.substring(0,idx), param.substring(idx + 1));
		}
		return map;
	}
	
	/**
	 * 进行效验数据,异常时抛出EX000000
	 * 
	 * @param param
	 *            需要效验的MAP
	 * @param keys
	 *            参数列表key值.
	 * @throws TranException
	 */
	public static void doing(Map<String, Object> param, String... keys) throws Exception {
		log.info("参数效验中[{}]",param);
		for (String key : keys) {
			if (!param.containsKey(key) || param.get(key) == null || param.get(key).toString().trim().equals("")) {
				throw new BusinessException(ExcepCode.EX900000,"字段["+key+"]不能为空!");
			}
		}
		log.info("参数效验完成.");
	}
	
	/**
	 * 检验map中的指定参数是不是全部为空或不存在
	 * @param param
	 * @param keys
	 * @return
	 */
	public static void allEmptyCheck(Map<String, Object> param, String... keys) throws Exception {
		StringBuffer sb = new StringBuffer("");
		for (String key : keys) {
			sb.append(key).append(",");
			if (param.get(key) != null && !param.get(key).toString().trim().equals("")) {
				return;
			}
		}
		throw new Exception("字段["+sb.toString()+"]不能同时为空!");
	}

	/**
	 * @param parameters
	 * @param keys
	 *            需要重组map的key数组
	 * @return LinkedHashMap 按keys数组中的顺序排列
	 */
	public static Map<String, Object> recombinationMap(Map<String, Object> parameters, String... keys) {
		Map<String, Object> results = new LinkedHashMap<String, Object>();
		for (String key : keys) {
			if (parameters.containsKey(key)) {
				results.put(key, parameters.get(key));
			}
		}
		return results;
	}
	
	/**
	 * 获得网关表单提交内容
	 * @param url 提交地址
	 * @param paramMap 表单参数
	 * @return
	 */
	public static String getGatewayForm(String url,Map<String, Object> paramMap) {
		return getGatewayForm(url, paramMap, null);
	}
	
	/**
	 * 获得表单提交内容
	 * @param url 表单提交地址
	 * @param forwardUrl 提交转发地址,该地址是签约网站地址,通过签约网站再跳转到渠道,如果该地址不存在,则不转发
	 * @param paramMap
	 * @return
	 */
	public static String getGatewayForm(String url,Map<String, Object> paramMap,String forwardUrl) {
		StringBuffer sbHtml = new StringBuffer("");
		sbHtml.append("<script language=\"javascript\">window.onload=function(){document.payfrm.submit();}</script>\n");
		if(StringUtil.isNotEmpty(forwardUrl)){
			sbHtml.append("<form id=\"payfrm\" name=\"payfrm\" action=\""+forwardUrl+"\" method=\"post\" >\n");
			sbHtml.append(getHtml("_f_url",url));
		}else{
			sbHtml.append("<form id=\"payfrm\" name=\"payfrm\" action=\""+url+"\" method=\"post\" >\n");
		}
		for (String key : paramMap.keySet()) {
			sbHtml.append(getHtml(key,ObjectUtil.getString(paramMap.get(key))));
		}
		sbHtml.append("</form>");
		return sbHtml.toString();
	}
	
	public static String getHtml(String name, String value) {
		return "<input type=\"hidden\" name=\"" + name + "\" value=\"" + value
				+ "\" />\n";
	}
}
