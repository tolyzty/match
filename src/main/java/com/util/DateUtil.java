package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 日期函数
 * @author xiejinzhong
 *
 */
public class DateUtil {
	public static String FULL_TIME_FORMAT_EN = "yyyyMMddHHmmSS";
	public static String FULL_TIME_FORMAT_CH = "yyyy年MM月dd日HH点mm分";
	
	/**
	 * 日期格式  dd/MM/yyyy 分割线:斜杠
	 */
	public static String FORMAT_DD_MM_YYYY_SLASH = "dd/MM/yyyy";
	/**
	 * 日期格式  dd/MM/yyyy 分割线:斜杠
	 */
	public static String FORMAT_MM_DD_YYYY_SLASH = "MM/dd/yyyy";
	
	/**
	 * 日期格式 yyyyMMdd
	 */
	public static String FORMAT_YYYYMMDD = "yyyyMMdd";
	
	/**
	 * 获取日期
	 * @param format 日期格式
	 * @param op +1 加1  -1 减1
	 * @return String
	 */
	public static String get(String format,int op) {	
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();		
		calendar.setTime(new Date());		
		calendar.add(Calendar.DAY_OF_MONTH, op);
		return sdf.format(calendar.getTime());
	}

	
	public static String datetrans(XMLGregorianCalendar calendar) {
        DateFormat d = new SimpleDateFormat(FULL_TIME_FORMAT_EN);
		return d.format(calendar.toGregorianCalendar().getTime());

	}
	
	
	public static String datetrans1(XMLGregorianCalendar calendar) {
        DateFormat d = new SimpleDateFormat(FULL_TIME_FORMAT_CH);
		return d.format(calendar.toGregorianCalendar().getTime());

	}
	
	public static String convertDateToString(Date date,String format){
		DateFormat d = new SimpleDateFormat(format);
		return d.format(date);
	}
	public static Object convertStringToDate(String sdate,String format){
		DateFormat d = new SimpleDateFormat(format);
		
		try {
			return d.parseObject(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取当月第一天
	 * @return
	 */
	public static String getMonthFirstDay(){
		Calendar cal = Calendar.getInstance();   
		  
		SimpleDateFormat datef=new SimpleDateFormat("yyyyMMdd");  
          //当前月的第一天             
         cal.set(GregorianCalendar.DAY_OF_MONTH, 1);   
         Date beginTime=cal.getTime();  
         String beginTime1=datef.format(beginTime); 
         
         return beginTime1;
	}
	/**
	 * 获取当月最后一天
	 * @return
	 */
	public static String getMonthEndDay(){
		Calendar cal = Calendar.getInstance();   
		  
		SimpleDateFormat datef=new SimpleDateFormat("yyyyMMdd");  
         //当前月的最后一天      
         cal.set( Calendar.DATE, 1 );  
         cal.roll(Calendar.DATE, - 1 );  
         Date endTime=cal.getTime();  
         String endTime1=datef.format(endTime);  

         return endTime1;
	}
	/**
	 * 获取当前 月份
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		String currentDay = String.valueOf(cal.get(Calendar.MONTH) + 1);
		return currentDay;
	}

	/**
	 * 获取当前 日
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		String currentDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		return currentDay;
	}
	
	/**
	 * 获取指定类型的日期
	 * 
	 * @param sdate
	 *            字符型日期
	 * @param strFormatS
	 *            转换前格式
	 * @param strFormatE
	 *            转换后格式
	 * @return
	 */
	public static Date dateFormat(String sdate, String strFormatS) {

		SimpleDateFormat sdf = new SimpleDateFormat(strFormatS);
		try {
			return sdf.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
