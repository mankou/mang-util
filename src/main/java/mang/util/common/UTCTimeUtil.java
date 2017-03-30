package mang.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UTCTimeUtil {
	
	/*
	 * 其它时间格式 "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 * */
	private static String default_utcTimeFormat="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static String default_localTimeFormat="yyyy-MM-dd HH:mm:ss";
	private static String default_utcTimeZone="UTC";
	private static String default_localTimeZone="GMT+8";
	
	
	/**
	 * 获取当前时间的UTC时间字符串 时间格式用默认的.
	 * */
	public static String getCurrentUTCTimeStr() {
		return getCurrentUTCTimeStr(default_utcTimeFormat);
	}
	
	/**
	 * 获取当前时间的UTC时间字符串 时间格式可通过参数传入.
	 * */
	public static String getCurrentUTCTimeStr(String format){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(default_utcTimeZone));
		String str=df.format(cal.getTime());
		return str;
	}
	
	
	/**
	 * 将毫秒型时间转换成UTC时间格式
	 * */
	public static String convertLongToUtc(Long millis,String format){
		Date date=new Date(millis);
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(default_utcTimeZone));
		String str=df.format(date);
		return str;
	}
	
	
	/**
	 * 将毫秒型时间转换成UTC时间格式 时间格式按默认的走.
	 * @param millis 毫秒型时间
	 * @return String UTC时间字符串
	 */
	public static String getUTCTimeStr(Long millis){
		return convertLongToUtc(millis, default_utcTimeFormat);
	}
	
	/**
	 * 将本地时间字符串转换成utc时间字符串
	 * */
	public static String convertLocalToUtc(String localTimeStr, String localTimeFormater, String utcTimeFormater) {
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
	 * 将UTC时间转换为东八区时间
	 * 
	 * @param utcTimeStr UTC格式的时间字符串
	 * @param utcTimeFormat UTC时间格式  如 yyyy-MM-dd'T'HH:mm:ss'Z'
	 * @param localTimeFormat 本地时间格式字符串  如 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String convertUtcToLocal(String utcTimeStr,String utcTimeFormat,String localTimeFormat) {
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
	 * 将UTC时间字符串转换成东八区时间字符串,至于utc时间格式与本地时间格式采用默认的
	 * @param utcTimeStr UTC时间字符串
	 * @return 本地时间格式字符串
	 * */
	public static String convertUtcToLocal(String utcTimeStr){
		return convertUtcToLocal(utcTimeStr,default_utcTimeFormat,default_localTimeFormat);
	}
	
	/**
	 * utc格式时间字符串转换成java.util.Date类型 这里采用默认的时间格式字符串 yyyy-MM-dd'T'HH:mm:ss'Z'
	 * @param utcTimeStr utc时间格式字符串
	 * 
	 * */
	public Date convertUtc2Date(String utcTimeStr){
		return convertUtc2Date(utcTimeStr,default_utcTimeFormat);
	}
	
	/**
	 * utf格式时间字符串转换成java.util.Date类型.
	 * @param utcTimeStr utc时间字符串
	 * @param format 时间格式 如 yyyy-MM-dd'T'HH:mm:ss'Z'
	 * */
	public Date convertUtc2Date(String utcTimeStr,String format){
		return convertStr2Date(utcTimeStr,format,default_utcTimeZone);
	}
	
	public Date convertLocal2Date(String localTimeStr,String formater){
		return convertStr2Date(localTimeStr,formater,default_utcTimeZone);
	}
	
	/**
	 * 字符串转时间.
	 * */
	public Date convertStr2Date(String localTimeStr,String format,String timeZone){
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		Date date=null;
		try {
			date = df.parse(localTimeStr);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	

	public static void main(String[] args) {
		System.out.println("当前时间-getUTCTimeStr:"+getCurrentUTCTimeStr());
		System.out.println("当前时间-getUTCTimeStr(参数):"+getCurrentUTCTimeStr("yyyy-MM-dd HH:mm:ss"));
		System.out.println();
		
		String utcTimeStr="2015-09-01T01:00:00Z";
		String localTimeStr=convertUtcToLocal(utcTimeStr);
		System.out.println("utcTimeStr:"+utcTimeStr);
		System.out.println("localTimeStr:"+localTimeStr);
		
		
	}
}
