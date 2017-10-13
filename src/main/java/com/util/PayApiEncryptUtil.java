package com.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支付Api加密工具类
 * @author jinzhong
 *
 */
public class PayApiEncryptUtil {
	private static Logger log = LoggerFactory.getLogger(PayApiEncryptUtil.class);
	/**
	 * 各版本加密存储Map
	 * 	key:版本号 value:加密配置项
	 */
	private static Map<String,Map<String,String[]>> EncryptVersionMap = new HashMap<String, Map<String,String[]>>();
	
	/**
	 * 加密配置 V3.0
	 * 	key:接口名称 value:签名字段(包含顺序)
	 */
	private static Map<String,String[]>	EncryptVersion30 = new HashMap<String,String[]>();
	
	/**
	 * 初始化 V3.0加密配置
	 */
	static{
		EncryptVersion30.put("pay", new String[]{"payType","consumerNo","merOrderNum","transAmt","bankCode","frontUrl","backUrl","frontUrl"});
		
		EncryptVersionMap.put("3.0", EncryptVersion30);
	}
	
	/**
	 * 获得签名元数据
	 * @param ac
	 * @param paramMap
	 * @return
	 */
	public static String getSignStr(String ac,Map<String,Object> paramMap){
		log.info("API:获得签名 [ac={}] [paramMap={}]",ac,paramMap);
		String version = ObjectUtil.getString(paramMap.get("version"));
		Map<String,String[]> EncryptMap = EncryptVersionMap.get(version);
		String[] keys = EncryptMap.get(ac);
		Map<String,Object> signMap = MapUtils.recombinationMap(paramMap, keys);
		String signStr = MapUtils.getSignStrByMap(signMap, true);
		log.info("获得签名元数据(忽略空值):[{}]",signStr);
		return signStr;
	}
	
	/**
	 * 获得签名
	 * @param ac
	 * @param signKey
	 * @param paramMap
	 * @return
	 */
	public static String getSign(String ac,String signKey,Map<String,Object> paramMap){
		String signStr = getSignStr(ac, paramMap);
		return MD5.encryption(signStr + "&" + signKey).toUpperCase();
	}
}
