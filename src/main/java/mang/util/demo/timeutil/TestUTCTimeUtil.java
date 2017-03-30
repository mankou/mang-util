package mang.util.demo.timeutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import mang.util.common.UTCTimeUtil;

public class TestUTCTimeUtil {
	public static void main(String[] args) throws ParseException {
		String utcStr="2017-03-27T08:29:00.000Z";
		String localStr=UTCTimeUtil.convertUtcToLocal(utcStr);
		System.out.println(localStr);
		
		SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date utcDate=utcFormat.parse(utcStr);
		System.out.println(utcDate.getTime());
		
		
		SimpleDateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		localFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		Date localDate=localFormat.parse(localStr);
		System.out.println(localDate.getTime());
		
		Date now=new Date();
		String nowUTCStr=UTCTimeUtil.getUTCTimeStr(now.getTime());
		System.out.println(nowUTCStr);
		
		String nowUTCStr_formater=UTCTimeUtil.convertLongToUtc(now.getTime(),"yyyy-MM-dd HH:mm:ss");
		System.out.println(nowUTCStr_formater);
		
	}
}
