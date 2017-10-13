package com.util;

import java.math.BigDecimal;
import java.util.Map;

public class ObjectUtil {
	
	//private static final int BUFFER_SIZE = 16 * 1024 ;
	public static boolean isNone(String st) {
		return st == null || st.equals("");
	}
	
	public static String getString(String st) {
		return st == null ? "" : st.trim();
	}
	
	public static int getInt(String st) {
		return st == null || (st.trim()).equals("") ? 0 : Integer.parseInt(st.trim());
	}
	
	public static int getInt(Object st) {
		return st == null ? 0 : Integer.parseInt(st.toString().trim());
	}
	
	public static long getLong(Object st) {
		return st == null || (st.toString().trim()).equals("") ? 0 : Long.parseLong(st.toString().trim());
	}
	public static long getLong(String st) {
		return st == null || (st.trim()).equals("") ? 0 : Long.parseLong(st.trim());
	}
	
	public static float getFloat(String st) {
		return st == null || (st.trim()).equals("") ? 0f : Float.parseFloat(st.trim());
	}
	
	public static double getDouble(String st) {
		return st == null || (st.trim()).equals("") || "null".equalsIgnoreCase(st) ? 0d : Double.parseDouble(st.trim());
	}
	public static double getDouble(Object obj) {
		return getDouble(obj == null ? null : obj.toString());
	}
	
	public static String getString(Object o) {
		return o == null ? "" : o.toString();
	}
	
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String) {
			if ("".equals(((String) obj).trim())) {
				return true;
			}
		} else if (obj instanceof Object[]) {
			if (((Object[]) obj).length == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isNotEmpty(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj instanceof String) {
			if ("".equals(((String) obj).trim())) {
				return false;
			}
		} else if (obj instanceof Object[]) {
			if (((Object[]) obj).length == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 转换为BigDecimal 并保留两位小数
	 * @param obj
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object obj){
		obj = obj == null ? "0" : obj.toString();
		return new BigDecimal(obj.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public static void copyMap(Map<String,Object> srcMap,Map<String,Object> tagMap,String... keys){
		for (String key : keys) {
			tagMap.put(key, srcMap.get(key));
		}
	}
}
