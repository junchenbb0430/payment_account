package com.ljs.account.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
 

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
 

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DateUtil extends DateUtils  {
	
	/**
	 * HH:mm:ss 24小时制
	 */
	public static final String FULL_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static final String FULL_DATETIME_PATTERN_YYYY_SS="yyyyMMddHHmmss";
	
	/**
	 *  yyyy-MM-dd 标准日期格式
	 */
	public static final String STANDARD_DATE_PATTERN = "yyyy-MM-dd";
	/**
	 *  yyyyMMdd 日期格式
	 */
	public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
	
	/**
	 * 精确到小时
	 */
	public static final String DATETIME_PATTERN_HOUR = "yyyy-MM-dd HH";
	
	/**
	 * 截取时，分，秒时间格式
	 */
	public static final String DATETIME_PATTERN_HOUR_SENCOND ="HH:mm:ss";
	/**
	 * 截取时，分，秒时间格式
	 */
	public static final String TIME_PATTERON_HOUR_SENCOND = "HHmmss";
	
	/**
	 * hh:mm:ss 12小时制
	 */
	public static final String FULL_DATETIME_PATTERN12 = "yyyy-MM-dd hh:mm:ss";
	 

	public static Date  formateDate(String dateStr) {
		 
		return formateDate(dateStr, null);
		
	}
	
	public static Date formateDate(String dateStr,String pattern) {
		if(StringUtils.isBlank(dateStr)) {
			return null;
		}
		String datePattern = null;
		if(StringUtils.isBlank(pattern)) {
			datePattern = STANDARD_DATE_PATTERN;
		}else {
			datePattern = pattern;
		}
		SimpleDateFormat   sdf = new SimpleDateFormat(datePattern);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		 
		} catch (ParseException e) {
			throw new IllegalArgumentException("日期解析异常:"+dateStr,e);
		}
		return date;
	}
	
	public static String  formateDateStr(Date date) {
		if(null == date) {
			return null;
		}
		SimpleDateFormat   sdf = new SimpleDateFormat(FULL_DATETIME_PATTERN);
		return sdf.format(date);
	}
	
	public static String formateDateStr(Date date,String pattern) {
		if(Objects.isNull(date)) return null;
		SimpleDateFormat   sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	 
   
	
	public static Date getNextDate(String date) {
		if(StringUtils.isBlank(date)) {
			return null;
		}
		Date  beginDate = formateDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date nextDate = calendar.getTime();
		return nextDate;
	}
	
	public static Date getNextDate(String date,String pattern) {
		if(StringUtils.isBlank(date)) {
			return null;
		}
		Date  beginDate = formateDate(date,pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date nextDate = calendar.getTime();
		return nextDate;
	}
	
	public static String getCurrentDateStr() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 return sdf.format(date);
	}
	
	public static String getCurrentYear() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
		 return sdf.format(date);
	}
	
	public static int comparedTwoDate(String dateStr1,String dateStr2) {
		Date dateOne = formateDate(dateStr1);
		Date dateTwo = formateDate(dateStr1);
		if(dateOne.compareTo(dateTwo)==0) {
			return 0;
		}else if(dateOne.compareTo(dateTwo) >1) {
			return 1;
		}else {
			return -1;
		}
	}
	
	public static int comparedTwoDate(Date dateOne,Date dateTwo) {
		if(dateOne.compareTo(dateTwo)==0) {
			return 0;
		}else if(dateOne.compareTo(dateTwo) >1) {
			return 1;
		}else {
			return -1;
		}
	}
	
	/**
	 * 比较与当前日期大小
	* @param dateStr
	* @return
	 */
	public static int comparedCurrentDate(String dateStr) {
		Date date = formateDate(dateStr);
		Date currentDate = formateDate(getCurrentDateStr());
		if(date.compareTo(currentDate)>=0) {
			return 1;
		}
		return -1;
	}
	
	/**
	 * 根据当前日期获取下一个日期
	* @param currDate
	* @return
	 */
	public static Date getNextDate(Date currDate) {
		if(null == currDate) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date nextDate = calendar.getTime();
		return nextDate;
	}
	/**
	 * 获取当前日期的下一个小时
	* Date
	 */
	public static Date getCurrentNextHourDate(Date currDate) {
		if(null == currDate) return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		calendar.add(Calendar.HOUR_OF_DAY,1);
		Date nextDate = calendar.getTime();
		return nextDate;
	}
	/**
	 * 校验传输日期是否能解析成功
	* boolean
	 */
	public static boolean validateDateStr(String dateStr) {
		boolean validateFlag = true;
		try {
			  formateDate(dateStr);
		}catch(Exception ex) {
			validateFlag = false;
			ex.printStackTrace();
		}
		return validateFlag;
	}
	
	public static Date getNewDate(Date currDate) {
		if(null == currDate) 
			return new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currDate);
		calendar.add(Calendar.YEAR, -3);
		Date newDate = calendar.getTime();
		return newDate;
	}
	

	public static void main(String[] args) {
		System.out.println(DateUtil.getCurrentYear());
		Date date = DateUtil.formateDate("2018-12-17",STANDARD_DATE_PATTERN);
		System.out.println(date.toString());
		System.out.println(DateUtil.comparedCurrentDate("2018-12-21"));
		Date dd = DateUtil.formateDate("2018-12-20 12:12:12");
		System.out.println(DateUtil.formateDateStr(dd, STANDARD_DATE_PATTERN));
	}
}
