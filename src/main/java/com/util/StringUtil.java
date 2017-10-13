package com.util;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StringUtil {
	public static final boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}else if("null".equalsIgnoreCase(obj.toString())){
			return true;
		}
		return org.apache.commons.lang.StringUtils.isEmpty(obj.toString());
	}
	public static final boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}
	
	public static String toString(Object obj,String defVal){
		if(isEmpty(obj)){
			return defVal;
		}
		return obj.toString();
	}
	
	/**
	 * 根据参数map替换字符串中的变量，针对模板型动态字符串处理 区分大小写 ，传入的map中的key只要在模板中有都会替换掉
	 * @param srcStr 模板字符串 如：【{oemName}】您本次获取的验证码为{code}
	 * @param paramMap 要替换的参数map ，会进行轮循，所以没用的参数尽量不要多加进来
	 * @return
	 */
	public static String replaceByMap(String srcStr, Map<String, ?> paramMap) {
		if (paramMap == null) {
			return srcStr;
		}
		Set<String> keySet = paramMap.keySet();
		String value;
		String str;
		for (String key : keySet) {
			value = String.valueOf(paramMap.get(key));
			str = "\\{" + key + "}";
			srcStr = srcStr.replaceAll(str, value);
//			str = "\\$\\{" + key + "}";
//			srcStr = srcStr.replaceAll(str, value);
//			str = "\\#\\{" + key + "}";
//			srcStr = srcStr.replaceAll(str, value);
		}
		return srcStr;
	}


	/**
	 * 
	 * JAVA获得0-9,a-z,A-Z范围的随机数
	 * 
	 * @param length
	 *            随机数长度
	 * 
	 * @return String
	 * 
	 */

	public static String getRandomChar(int length) {
		char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z',

				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z' };

		Random random = new Random();

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < length; i++) {

			buffer.append(chr[random.nextInt(62)]);

		}

		return buffer.toString();

	}
	
	/**
	 * 
	 * JAVA获得0-9,a-z,A-Z范围的随机数
	 * 不包含模糊字符,如(0oO,I1lL1)
	 * @param length
	 *            随机数长度
	 * 
	 * @return String
	 * 
	 */

	public static String getRandomCharNoFuzzy(int length) {
		char[] chr = { '1', '2', '3', '4', '5', '6', '7', '8', '9',

				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',  'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z',

				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',  'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z' };
		Random random = new Random();

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < length; i++) {

			buffer.append(chr[random.nextInt(chr.length)]);

		}

		return buffer.toString();

	}
	
	/**
	 * 字符串填充(前补)
	 * @param str 原字符串
	 * @param fillStr 要补位字符
	 * @param len 填充完成后长度
	 * @return
	 */
	public static String StringFill(String str,String fillStr,int len){
		if(str == null){
			str = "";
		}
		int strLen = str.length();
		for (int i = 0; i < (len - strLen); i++) {
			str = fillStr + str;
		}
		return str;
	}
}
