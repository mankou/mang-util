package mang.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UTCTimeUtil {
	private static String default_utcTimeFormat="yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static String default_localTimeFormat="yyyy-MM-dd HH:mm:ss";
	
	public static String getUTCTimeStr() {
		return getUTCTimeStr(default_utcTimeFormat);
	}
	
	public static String getUTCTimeStr(String format){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		String str=df.format(cal.getTime());
		return str;
	}
	

	/**
	 * 将UTC时间转换为东八区时间
	 * 
	 * @param utcTimeStr
	 * @return
	 */
	public static String getLocalTimeFromUTC(String utcTimeStr,String utcTimeFormat,String localTimeFormat) {
		Date UTCDate = null;
		String localTimeStr = null;
		try {
			
			SimpleDateFormat utcFormat = new SimpleDateFormat(utcTimeFormat);
			utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			UTCDate = utcFormat.parse(utcTimeStr);
			
			
			SimpleDateFormat localFormat = new SimpleDateFormat(localTimeFormat);
			localFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			localTimeStr = localFormat.format(UTCDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return localTimeStr;
	}
	
	
	public static String getLocalTimeFromUTC(String UTCTime){
		return getLocalTimeFromUTC(UTCTime,default_utcTimeFormat,default_localTimeFormat);
	}

	public static void main(String[] args) {
		System.out.println("getUTCTimeStr:"+getUTCTimeStr());
		System.out.println("getUTCTimeStr(参数):"+getUTCTimeStr("yyyy-MM-dd HH:mm:ss"));
		
		String utcTimeStr="2015-09-01T01:00:00Z";
		String localTimeStr=getLocalTimeFromUTC(utcTimeStr);
		System.out.println();
		System.out.println("utcTimeStr:"+utcTimeStr);
		System.out.println("localTimeStr:"+localTimeStr);
		
		
	}
}
