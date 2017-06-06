package com.goldgov.origin.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具类
 * @author LiuHG
 * @version 1.0
 */
public abstract class DateUtils {

	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static final String TIME_PATTERN = "HH:mm:ss";

	private static final SimpleDateFormat dataFormat = new SimpleDateFormat();
	
	public static String formatDate(Date date,String pattern){
		if(pattern == null){
			dataFormat.applyPattern(DATE_TIME_PATTERN);
		}
		return dataFormat.format(date);
	}
	
	public static String formatDate(Date date){
		return formatDate(date,null);
	}
	
	public static Date parseDate(String dateStr,String pattern){
		if(pattern == null){
			dataFormat.applyPattern(DATE_TIME_PATTERN);
		}
		try {
			return dataFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException("日期转换解析错误，格式：" + pattern,e);
		}
	}
	
	public static Date parseDate(String dateStr){
		return parseDate(dateStr,null);
	}
}
