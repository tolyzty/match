package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 系统公用函数管理
 * 棠棣科技
 * @author Administrator
 */
public class DateUtils {
	/**日期格式:yyyyMMddHHmmss */
	public static final String DATETIME_FORMAT_1 = "yyyyMMddHHmmss";
	/**日期格式:yyyy-MM-dd HH:mm:ss */
	public static final String DATETIME_FORMAT_4 = "yyyy-MM-dd HH:mm:ss";
	/**日期格式:yyyyMMdd */
	public static final String DATETIME_FORMAT_2 = "yyyyMMdd";
	/**日期格式:yyyy-MM-dd */
	public static final String DATETIME_FORMAT_3 = "yyyy-MM-dd";
	/**日期格式:MMddHHmm */
	public static final String TIME_FORMAT_1 = "MMddHHmm";
	/**日期格式:HHmmss */
	public static final String TIME_FORMAT_2 = "HHmmss";

	/**
	 * 获取yyyyMMddHHmmss的当时时间格式
	 * @return
	 */
	public static String getCurrentDateTime() {
		return formatCurrentDateTime("yyyyMMddHHmmss");
	}

	/**
	 * 获取yyyy-MM-dd HH:mm:ss的当时时间格式
	 * @return
	 */
	public static String getCurrentDateTime2() {
		return formatCurrentDateTime("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取yyyyMMdd的当时时间格式
	 * @return
	 */
	public static String getCurrentDate() {
		return formatCurrentDateTime("yyyyMMdd");
	}

	/**
	 * 获取yyyy-MM-dd的当时时间格式
	 * @return
	 */
	public static String getCurrentDate2() {
		return formatCurrentDateTime("yyyy-MM-dd");
	}

	/**
	 * 获取MMddHHmm的当时时间格式
	 * @return
	 */
	public static String getCurrentTime() {
		return formatCurrentDateTime("MMddHHmm");
	}

	/**
	 * 获取HHmmss的当时时间格式
	 * @return
	 */
	public static String getCurrentTime2() {
		return formatCurrentDateTime("HHmmss");
	}

	/**
	 * 根据传进来的格式，将时间进行格式化并返回
	 * @return
	 */
	public static String formatCurrentDateTime(String formatText) {
		Calendar calendar = Calendar.getInstance();
		return convertDateToStr(calendar.getTime(), formatText);
	}

	/**
	 * 将日期格式进行转化，返回字符串格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertDateToStr(Date date, String pattern) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将日期格式进行转化，返回字符串格式
	 * @param date1
	 * @return
	 */
	public static String convertDateFormat(String date1) {
		if (date1 == null) {
			return date1;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date2 = null;
		try {
			date2 = formatter.parse(date1);
		} catch (ParseException e) {
			return date1;
		}
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date3 = formatter.format(date2);
		return date3;
	}

	/**
	 * 将日期格式进行转化，返回字符串格式
	 * @param date
	 * @return
	 */
	public static String formatDateTOD3(String date) {
		Date d1 = new Date();
		try {
			if (StringUtils.isNotEmpty(date)) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				d1 = df.parse(date);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return d1.toString();
	}

	/**
	 * 将10位日期格式转化为8位并返回
	 * @param date1
	 * @return
	 */
	public static String formmat10to8(String date1) {
		if (date1 == null) {
			return date1;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		try {
			date2 = formatter.parse(date1);
		} catch (ParseException e) {
			return date1;
		}
		formatter = new SimpleDateFormat("yyyyMMdd");
		String date3 = formatter.format(date2);
		return date3;
	}

	/**
	 * 将20位日期格式转化为14位并返回
	 * @param date1
	 * @return
	 */
	public static String formmat20to14(String date1) {
		if (date1 == null) {
			return date1;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2 = null;
		try {
			date2 = formatter.parse(date1);
		} catch (ParseException e) {
			return date1;
		}
		formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String date3 = formatter.format(date2);
		return date3;
	}
	
	/**
	 * 将字符转换为日期格式
	 */
	public static Date string2Date(String date,String formatDate){
		DateFormat df = new SimpleDateFormat(formatDate);  
		Date date1 =  null;
		try { 
			date1 = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}
	
	/**
	 * 比较日期大小 日期默认格式 yyyyMMdd
	 * @param beforeDate1 开始日期
	 * @param afterDate2 比较日期
	 * @return 如果 beforeDate > afterDate 返回 true
	 */
	public static boolean compareDate(String beforeDate, String afterDate){
		return compareDate(beforeDate,DATETIME_FORMAT_2,afterDate,DATETIME_FORMAT_2);
	}
	
	/**
	 * 日期大小比较
	 * @param beforeDate 开始日期
	 * @param afterDate 比较日期
	 * @param dateFormat 日期格式
	 * @return 如果 beforeDate > afterDate 返回 true
	 */
	public static boolean compareDate(String beforeDate, String afterDate,String dateFormat){
		return compareDate(beforeDate,dateFormat,afterDate,dateFormat);
	}
	
	/**
	 * 日期大小比较
	 * @param beforeDate 开始日期
	 * @param beforeFormat 开始日期格式
	 * @param afterDate 比较日期
	 * @param afterFormat 比较日期格式
	 * @return 如果 beforeDate > afterDate 返回 true
	 */
	public static boolean compareDate(String beforeDate,String beforeFormat, String afterDate,String afterFormat){
		boolean flag = false;
		try{
			Date dt1 = string2Date(beforeDate, beforeFormat);
            Date dt2 = string2Date(afterDate, afterFormat);
            int dd = dt1.compareTo(dt2);
            if (dd>=0) {
            	flag = true;
            } else{
                flag = false;
            }
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 字符串日期天数增减
	 * 
	 * @param srcDateStr 源日期字符串
	 * @param dateFormat 源日期字符串格式
	 * @param day 要增减的天数
	 * @return
	 */
	public static String dateAddDay(String srcDateStr,String dateFormat,int day){
		return dateAddDay(srcDateStr,dateFormat,day,dateFormat);
	} 
	
	/**
	 * 字符串日期天数增减
	 * 
	 * @param srcDateStr 源日期字符串
	 * @param srcFormat 源日期字符串格式
	 * @param day 要增减的天数
	 * @param targetFormat 目标日期格式
	 * @return
	 */
	public static String dateAddDay(String srcDateStr,String srcFormat,int day,String targetFormat){
		String value = "";
		Date date = string2Date(srcDateStr,srcFormat);
		date  = dateAddDay(date,day);
		value = convertDateToStr(date,targetFormat);
		return value;
	} 
	
	/**
	 * 日期加一天
	 * @param nowDate
	 * @return
	 */
	public static Date getNextDate(Date nowDate){
		return dateAddDay(nowDate,1);
	}
	
	/**
	 * 日期加减天数
	 *java中对日期的加减操作
	 *gc.add(1,-1)表示年份减一.
	 *gc.add(2,-1)表示月份减一.
	 *gc.add(3.-1)表示周减一.
	 *gc.add(5,-1)表示天减一.
	 * @param nowDate
	 * @param day
	 * @return
	 */
	public static Date dateAddDay(Date nowDate,int day){
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(nowDate);
		gc.add(5, day);
		return gc.getTime();
	}
	
	/**
	 * 计算date2与date1之间的时差
	 * @param date
	 * @return 时间差，单位(毫秒)
	 */
	public static long getDateJetLag(Date date1,Date date2){
		return date2.getTime() - date1.getTime();
	}
	
	/**
	 * 计算date与当前时间的时间差
	 * @param date
	 * @return 时间差，单位(毫秒)
	 */
	public static long getDateJetLag(Date date){
		return getDateJetLag(date,new Date());
	}
	
	/**
	 * 计算date与当前时间时差
	 * @param date 字符串的日期
	 * @param format 字符串日期格式 如yyyyMMdd
	 * @return 时间差，单位(毫秒)
	 */
	public static long getDateJetLag(String date,String format){
		return getDateJetLag(string2Date(date, format));
	}
	
	/**
	 * 计算date2与date1时间时差
	 * @param date1 字符串的日期
	 * @param format1 字符串日期格式 如yyyyMMdd
	 * @param date2 字符串的日期
	 * @param format2 字符串日期格式 如yyyyMMdd
	 * @return 时间差，单位(毫秒)
	 */
	public static long getDateJetLan(String date1,String format1,String date2,String format2){
		return getDateJetLag(string2Date(date1, format1),string2Date(date2, format2));
	}
	
	public static void main(String[] args) {
		long lag = DateUtils.getDateJetLag(DateUtils.string2Date("20160520155045", DateUtils.DATETIME_FORMAT_1), new Date());
		//将毫秒转为分钟
		lag = (lag / 1000l / 60l);
		System.out.println(lag);
	}

}
