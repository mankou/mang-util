package mang.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * UTC时间格式处理
 * @author mang
 * */
public class UTCTimeUtil {
	
	/*
	 * 其它时间格式 "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 * */
	private static String default_utcTimeFormat="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static String default_localTimeFormat="yyyy-MM-dd'T'HH:mm:ss+08:00";
	private static String default_utcTimeZone="UTC";
	private static String default_localTimeZone="GMT+8";
	
	
	/**
	 * 获取当前时间的UTC时间字符串 时间格式用默认的.
	 * @return String
	 * */
	public static String getCurrentUTCTimeStr() {
		return getCurrentUTCTimeStr(default_utcTimeFormat);
	}
	
	/**
	 * 获取当前时间的UTC时间字符串 时间格式可通过参数传入.
	 * @param format utc时间格式
	 * @return String
	 * */
	public static String getCurrentUTCTimeStr(String format){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(default_utcTimeZone));
		String str=df.format(cal.getTime());
		return str;
	}
	
	/**
	 * 将毫秒型时间转换成UTC时间格式 时间格式按默认的走.
	 * @param millis 毫秒型时间
	 * @return String UTC时间字符串
	 */
	public static String getUTCTimeStr(Long millis){
		return convertLong2UTC(millis, default_utcTimeFormat);
	}
	
	
	/**
	 * 将毫秒型时间转换成UTC时间格式
	 * @param millis 毫秒时间
	 * @param utcFormat utc格式字符串
	 * @return String
	 * */
	public static String convertLong2UTC(Long millis,String utcFormat){
		Date date=new Date(millis);
		SimpleDateFormat df = new SimpleDateFormat(utcFormat);
		df.setTimeZone(TimeZone.getTimeZone(default_utcTimeZone));
		String str=df.format(date);
		return str;
	}
	
	
	public static String convertLong2Local(Long millis){
		return convertLong2Local(millis, default_localTimeFormat);
	}
	
	
	public static String convertLong2Local(Long millis,String timeFormat){
		Date date=new Date(millis);
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		df.setTimeZone(TimeZone.getTimeZone(default_localTimeZone));
		String str=df.format(date);
		return str;
	}
	
	
	/**
	 * 将UTC时间字符串转换成东八区时间字符串,至于utc时间格式与本地时间格式采用默认的
	 * @param utcTimeStr UTC时间字符串
	 * @return 本地时间格式字符串
	 * */
	public static String convertUTC2Local(String utcTimeStr){
		return convertUTC2Local(utcTimeStr,default_utcTimeFormat,default_localTimeFormat);
	}
	
	/**
	 * 将UTC时间字符串转换成本地时间字符串,至于utc时间格式 yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 * @param utcTimeStr UTC时间字符串
	 * @param localTimeFormat 本地时间格式  如 yyyy-MM-dd'T'HH:mm:ss+08:00
	 * @return 本地时间格式字符串
	 * */
	public static String convertUTC2Local(String utcTimeStr,String localTimeFormat){
		return convertUTC2Local(utcTimeStr,default_utcTimeFormat,localTimeFormat);
	}

	/**
	 * 将UTC时间转换为东八区时间
	 * 
	 * @param utcTimeStr UTC格式的时间字符串
	 * @param utcTimeFormat UTC时间格式  如 yyyy-MM-dd'T'HH:mm:ss'Z'
	 * @param localTimeFormat 本地时间格式字符串  如 yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String convertUTC2Local(String utcTimeStr,String utcTimeFormat,String localTimeFormat) {
		Date UTCDate = null;
		String localTimeStr = null;
		try {
			
			SimpleDateFormat utcFormat = new SimpleDateFormat(utcTimeFormat);
			utcFormat.setTimeZone(TimeZone.getTimeZone(default_utcTimeZone));
			UTCDate = utcFormat.parse(utcTimeStr);
			
			
			SimpleDateFormat localFormat = new SimpleDateFormat(localTimeFormat);
			localFormat.setTimeZone(TimeZone.getTimeZone(default_localTimeZone));
			localTimeStr = localFormat.format(UTCDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return localTimeStr;
	}
	
	
	/**
	 * utc格式时间字符串转换成java.util.Date类型 这里采用默认的时间格式字符串 yyyy-MM-dd'T'HH:mm:ss'Z'
	 * @param utcTimeStr utc时间格式字符串
	 * @return Date
	 * */
	public static Date convertUTC2Date(String utcTimeStr){
		return convertUTC2Date(utcTimeStr,default_utcTimeFormat);
	}
	
	
	/**
	 * utf格式时间字符串转换成java.util.Date类型.
	 * @param utcTimeStr utc时间字符串
	 * @param utcFormat 时间格式 如 yyyy-MM-dd'T'HH:mm:ss'Z'
	 * @return Date 
	 * */
	public static Date convertUTC2Date(String utcTimeStr,String utcFormat){
		return DateUtil.parse(utcTimeStr,utcFormat,default_utcTimeZone);
	}
	
	
	/**
	 * UTC格式字符串转换成毫秒型.
	 * @param utcTimeStr UTC格式的时间字符串
	 * @param utcFormat UTC格式的时间字符串的时间格式 如yyyy-MM-dd'T'HH:mm:ss.SSS'Z
	 * @return Long 毫秒数
	 * */
	public static Long convertUTC2Long(String utcTimeStr,String utcFormat){
		Date date=DateUtil.parse(utcTimeStr,utcFormat,default_utcTimeZone);
		return date.getTime();
	}
	
	/**
	 * UTC格式字符串转换成毫秒型.
	 * @param utcTimeStr UTC格式的时间字符串  这里使用默认的时间格式 yyyy-MM-dd'T'HH:mm:ss.SSS'Z
	 * @return Long 毫秒数
	 * */
	public static Long convertUTC2Long(String utcTimeStr){
		Date date=DateUtil.parse(utcTimeStr,default_utcTimeFormat,default_utcTimeZone);
		return date.getTime();
	}
	

	/**
	 * 将本地时间字符串转换成utc时间字符串
	 * @param localTimeStr 本地时间字符串
	 * @param localTimeFormater 本地时间格式
	 * @param utcTimeFormater UTC时间格式
	 * @return String
	 * */
	public static String convertLocalToUTC(String localTimeStr, String localTimeFormater, String utcTimeFormater) {
		SimpleDateFormat df_local = new SimpleDateFormat(localTimeFormater);
		df_local.setTimeZone(TimeZone.getTimeZone(default_localTimeZone));

		SimpleDateFormat df_utc = new SimpleDateFormat(utcTimeFormater);
		df_utc.setTimeZone(TimeZone.getTimeZone(default_utcTimeZone));

		try {
			Date date = df_local.parse(localTimeStr);
			String utcTimeStr = df_utc.format(date);
			return utcTimeStr;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/**
	 * 将本地时间转换成Date类型
	 * @param localTimeStr 本地时间字符串
	 * @param localFormater 本地时间字符串的时间格式
	 * @return Date
	 * */
	public static Date convertLocal2Date(String localTimeStr,String localFormater){
		return DateUtil.parse(localTimeStr,localFormater,default_localTimeZone);
	}
	
	
	public static String getDefault_utcTimeFormat() {
		return default_utcTimeFormat;
	}

	public static void setDefault_utcTimeFormat(String default_utcTimeFormat) {
		UTCTimeUtil.default_utcTimeFormat = default_utcTimeFormat;
	}

	public static String getDefault_localTimeFormat() {
		return default_localTimeFormat;
	}

	public static void setDefault_localTimeFormat(String default_localTimeFormat) {
		UTCTimeUtil.default_localTimeFormat = default_localTimeFormat;
	}

	public static String getDefault_utcTimeZone() {
		return default_utcTimeZone;
	}

	public static void setDefault_utcTimeZone(String default_utcTimeZone) {
		UTCTimeUtil.default_utcTimeZone = default_utcTimeZone;
	}

	public static String getDefault_localTimeZone() {
		return default_localTimeZone;
	}

	public static void setDefault_localTimeZone(String default_localTimeZone) {
		UTCTimeUtil.default_localTimeZone = default_localTimeZone;
	}

	public static void main(String[] args) {
		System.out.println("当前时间-getUTCTimeStr:"+getCurrentUTCTimeStr());
		System.out.println("当前时间-getUTCTimeStr(参数):"+getCurrentUTCTimeStr("yyyy-MM-dd HH:mm:ss"));
		System.out.println();
		
		String utcTimeStr="2015-09-01T01:00:00Z";
		String localTimeStr=convertUTC2Local(utcTimeStr);
		System.out.println("utcTimeStr:"+utcTimeStr);
		System.out.println("localTimeStr:"+localTimeStr);
		
	}
}
