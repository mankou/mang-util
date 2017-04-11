package mang.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	private final static String default_timeFormat = "yyyy-MM-dd HH:mm:ss";
	private final static String default_timeZone="GMT+8";

	/**
	 * @return Date 返回当前时间
	 * 
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 
	 * @param timeStr 时间字符串
	 * @param format 时间格式
	 * @return Date 返回转换后的Date时间
	 * */
	public static Date parse(String timeStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = df.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转时间.
	 * 
	 * @param timeStr
	 *            时间字符串
	 * @param timeFormat
	 *            时间格式 如yyyy-MM-dd'T'HH:mm:ss.SSS'Z' yyyy-MM-dd'T'HH:mm:ss+08:00
	 * @param timeZone
	 *            时区 如 UTC GMT+8
	 * @return Date           
	 */
	public static Date parse(String timeStr, String timeFormat, String timeZone) {
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		Date date = null;
		try {
			date = df.parse(timeStr);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * @param timeStr 时间字符串
	 * @return Date 返回转换后的Date时间
	 **/
	public static Date parse(String timeStr) {
		return parse(timeStr, default_timeFormat);
	}
	
	/**
	 * @return Date 当天0点时间
	 * */
	public static Date getCurrentDayZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date=calendar.getTime();
		return date;
	}
	
	
	/**
	 * @return Date 当天0点时间
	 * */
	public static Date getCurrentMonthZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date=calendar.getTime();
		return date;
	}
	

	/**
	 * @return Date 当天0点时间
	 * */
	public static Date getCurrentYearZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date=calendar.getTime();
		return date;
	}
	
	
	/**
	 * 给定一个时间和间隔单位 算出下一个时间.
	 * @param date 时间 如果为空 则用当前时间
	 * @param interval 时间间隔
	 * @param unit 时间间隔单位 second:秒    minute:分钟 hour:小时 day:天 month:月 year:年
	 * @return Date
	 * */
	public static Date addTime(Date date,Integer interval,String unit){
		Calendar c = Calendar.getInstance(); //这句好像很浪费，我也不知道该怎么处理
		if(date!=null){
			c.setTime(date);
		}
		
		if("second".equals(unit)){
			c.add(Calendar.SECOND, interval);
		}else if("minute".equals(unit)){
			c.add(Calendar.MINUTE, interval); 
		}else if("hour".equals(unit)){
			c.add(Calendar.HOUR, interval); 
		}else if("day".equals(unit)){
			c.add(Calendar.DATE, interval);  
		}else if("month".equals(unit)){
			c.add(Calendar.MONTH,interval);
		}else if("year".equals(unit)){
			c.add(Calendar.YEAR, interval);
		}
		
		Date resultDate=c.getTime();
		return resultDate;
		
	}

}
