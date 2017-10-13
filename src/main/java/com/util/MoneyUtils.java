package com.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

/**
 * 金额转换类
 * 
 * @author xiejinzhong
 * 
 */
public class MoneyUtils {

	/**
	 * 金额 元 格式转换对象
	 */
	public static DecimalFormat FORMAT_YUAN = new DecimalFormat("0.00");

	/**
	 * 金额 分 格式转换对象
	 */
	public static DecimalFormat FORMAT_FEN = new DecimalFormat("0");

	/**
	 * 转成 分
	 * 
	 * @param obj
	 * @return
	 */
	public static Double toDoubleCent(Object obj) {
		if(obj == null){
			obj = 0;
		}
		return NumberUtils.toDouble(BigDecimal.valueOf(NumberUtils.toDouble(obj.toString())).multiply(new BigDecimal(100)).toString());
	}

	/**
	 * 转成 分
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStrCent(Object obj) {
		String cent = formatCent(toDoubleCent(obj));
		return cent == null || "0".equals(cent) ? "" : cent;
	}

	/**
	 * 转成 元
	 * 
	 * @param obj
	 * @return
	 */
	public static Double toDoubleYuan(Object obj) {
		if(obj == null){
			obj = 0;
		}
		return NumberUtils.toDouble(BigDecimal.valueOf(NumberUtils.toDouble(obj.toString())).divide(new BigDecimal(100)).toString());
	}

	/**
	 * 转成 元
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStrYuan(Object obj) {
		String yuan = formatYuan(toDoubleYuan(obj));
		return yuan == null || "0.00".equals(yuan) ? "" : yuan;
	}

	/**
	 * 格式化分 ，变成没有小数点的数字字符串
	 * 
	 * @param obj
	 * @return
	 */
	private static String formatCent(Object obj) {
		return FORMAT_FEN.format(obj);
	}

	/**
	 * 格式化元 ，变成两位小数点的数字字符串
	 * 
	 * @param obj
	 * @return
	 */
	private static String formatYuan(Object obj) {
		return FORMAT_YUAN.format(obj);
	}

	 public static void main(String[] args) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 System.out.println(toDoubleYuan(0.5));
	 }
	 
		/**
		 * 计算费用，最低收取 1分 [提供精确的小数位四舍五入处理,保留2位小数。 ]
		 * @param rate  点数
		 * @param txamt 分
		 * @param low   分
		 * @return 分
		 */
		public static int calculate(String rate,String txamt,double low) {
			double rate_ = toDoubleYuan(rate);
			double txamt_= toDoubleYuan(txamt);
		
			Double tmpTxAmt  = mul(rate_ , txamt_) ;
			Double tmpTxAmt_ = mul(round(tmpTxAmt.doubleValue(),2),100.00);
			
			if(tmpTxAmt_.intValue() <=0 ){
				tmpTxAmt_ = low ;
			}
			
			return tmpTxAmt_.intValue();
		}
		
		
		
		/**
		 * Double相乘
		 * @param v1
		 * @param v2
		 * @return
		 */
		public static Double mul(Double v1, Double v2) {  
			   BigDecimal b1 = new BigDecimal(v1.toString());  
			   BigDecimal b2 = new BigDecimal(v2.toString());  
			   return new Double(b1.multiply(b2).doubleValue());  
		}  
		/** 
		* 提供精确的小数位四舍五入处理。 
		* @param v 需要四舍五入的数字 
		* @param scale 小数点后保留几位 
		* @return 四舍五入后的结果 
		*/  
		public static double round(double v, int scale) {  
		   BigDecimal b = new BigDecimal(Double.toString(v));  
		   return b.divide(new BigDecimal("1"), scale, BigDecimal.ROUND_HALF_UP).doubleValue();  
		}  
		
}
