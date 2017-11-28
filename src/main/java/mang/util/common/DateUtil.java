package mang.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtil {
	private  static String default_timeFormat = "yyyy-MM-dd HH:mm:ss";
	private  static String default_timeZone="GMT+8";
	
	 public final static String MILLISECOND = "millisecond";
	 public final static String SECOND = "second";
	 public final static String MINUTE = "minute";
	 public final static String HOUR = "hour";
	 public final static String DAY = "day";
	 public final static String MONTH = "month";
	 public final static String YEAR = "year";
	

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
	 * 
	 * @return Date 获取本周一 0点0分
	 * */
	public static Date getCurrentWeekZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
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
		
		if(MILLISECOND.equals(unit)){
			c.add(Calendar.MILLISECOND, interval);
		}else if(SECOND.equals(unit)){
			c.add(Calendar.SECOND, interval);
		}else if(MINUTE.equals(unit)){
			c.add(Calendar.MINUTE, interval); 
		}else if(HOUR.equals(unit)){
			c.add(Calendar.HOUR, interval); 
		}else if(DAY.equals(unit)){
			c.add(Calendar.DATE, interval);  
		}else if(MONTH.equals(unit)){
			c.add(Calendar.MONTH,interval);
		}else if(YEAR.equals(unit)){
			c.add(Calendar.YEAR, interval);
		}
		
		Date resultDate=c.getTime();
		return resultDate;
		
	}
	
	/**
	 * 给指定的时间加上指定的毫秒数.
	 * @param date 时间
	 * @param millisecond 毫秒数
	 * @return 返回处理后的时间
	 * */
	public static Date addTime(Date date,int millisecond){
		Calendar c = Calendar.getInstance(); 
		if(date!=null){
			c.setTime(date);
		}
		c.add(Calendar.MILLISECOND, millisecond);
		Date result=c.getTime();
		return result;
	}
	
	
	/**
	 * 返回当前时间的字符串 按默认格式 yyyy-MM-dd HH:mm:ss
	 * @return String 格式化后时间字符串
	 * */
	public static String getCurrentDateString(){
		String str=getCurrentDateString(default_timeFormat);
		return str;
	}
	
	/**
	 * 返回当前时间的时间字符串 .
	 * 需自己传入时间格式
	 * @param format 日期格式  如yyyy-MM-dd yyyyMMddHHmmss
	 * @return String 格式化后的时间字符串
	 * */
	public static String getCurrentDateString(String format){
		Date date = getCurrentDate();
		return getDateString(date,format,default_timeZone);
	}
	
	
	/**
	 * 取时间字符串  
	 * 需自己传入时间格式
	 * @param date 时间
	 * @param format 日期格式  如yyyy-MM-dd yyyyMMddHHmmss
	 * @param timezone 时区 如GMT+8 UTC
	 * @return String 格式化后的时间字符串
	 * */
	public static String getDateString(Date date,String format,String timezone){
		if(date==null){
			date=getCurrentDate();
		}
		
		if(format==null||"".equals(format)){
			format="yyyy-MM-dd";
		}
		
		DateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		String str = sdf.format(date);
		return str;
	}
	
	
	
	/**
	 * 取时间字符串
	 * @param date 时间
	 * @param format 时间格式 yyyy-MM-dd HH:mm:ss
	 * @return String 时间字符串
	 * */
	public static String getDateString(Date date,String format){
		return getDateString(date, format,default_timeZone);
	}
	
	
	/**
	 * 取时间字符串  采用默认时间格式和时区
	 * @param date 时间
	 * @return String 时间字符串
	 * */
	public static String getDateString(Date date){
		return getDateString(date, default_timeFormat,default_timeZone);
	}
	
	
	/**
	 * 用于计算2个日期间的时间差 并转换成易读的方式 .
	 * <p>endTime-startTime的值，结果是易读的字符串 如 1年5月6天8小时7分5秒
	 * @param endTime 结束时间
	 * @param startTime 开始时间
	 * @param language 语言  en cn english chinese等 分别表示英文、中文
	 * @return String
	 * */
	public static String computeTimeInterval(Date endTime,Date startTime,String language){
		Long betweenSecond = (endTime.getTime()-startTime.getTime())/1000;//除以1000是为了转换成秒
		betweenSecond=Math.abs(betweenSecond); //取绝对值
		
		Long betweenMills=endTime.getTime()-startTime.getTime();
		
		boolean isEnglish=isEnglish(language);
		
		
		String millStr=isEnglish?"ms":"毫秒";
		String secStr=isEnglish?"s":"秒";
		String minStr=isEnglish?"min":"分";
		String houStr=isEnglish?"hour":"小时";
		String dayStr=isEnglish?"day":"天";
		String monStr=isEnglish?"month":"月";
		String yearStr=isEnglish?"year":"年";
		
		long year=betweenSecond/(365*24*3600);
		long month=betweenSecond%(365*24*3600)/(30*24*3600);
		long day=betweenSecond%(30*24*3600)/(24*3600);
		long hour=betweenSecond%(24*3600)/3600;
		long minute=betweenSecond%(3600)/60;
		long second=betweenSecond%(60);
		
		String result="";
		//注 如果是0年 0月 0天 0分 0秒 则返回0秒 而不能是"" 所以秒没有参加if判断
		//如果时间间隔是0秒 则显示毫秒数 否则不显示
		if(betweenSecond==0){
			result+=betweenMills+millStr;
		}else{
			result+=second+secStr;
		}
		
		if(minute!=0){
			result=minute+minStr+result;
		}
		if(hour!=0){
			result=hour+houStr+result;
		}
		if(day!=0){
			result=day+dayStr+result;
		}
		if(month!=0){
			result=month+monStr+result;
		}
		if(year!=0){
			result=year+yearStr+result;
		}
	    
	    return result;
	}
	
	
	private static boolean isEnglish(String language){
		language=language.toLowerCase();
		if("en".equals(language)||"english".equals(language)){
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 根据开始时间、结束时间 和毫秒时间间隔获取一组时间
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param millisecond 毫秒时间间隔
	 * @return 返回一组时间
	 * */
	public static List<Date> getDateList(Date startDate,Date endDate,int millisecond){
		List<Date> lis = new ArrayList<Date>();
		Date time = new Date(startDate.getTime());
		while (time.getTime() <= endDate.getTime()) {
			lis.add(time);
			time = DateUtil.addTime(time,millisecond);
		}
		return lis;
	}
	
	/**
	 * 根据开始时间、结束时间 和时间间隔获取一组时间
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param interval 时间间隔
	 * @param unit 时间间隔单位 second:秒    minute:分钟 hour:小时 day:天 month:月 year:年
	 * @return 返回一组时间
	 * */
	public static List<Date> getDateList(Date startDate,Date endDate,Integer interval, String unit){
		List<Date> lis = new ArrayList<Date>();
		Date time = new Date(startDate.getTime());
		while (time.getTime() <= endDate.getTime()) {
			lis.add(time);
			time = DateUtil.addTime(time,interval,unit);
		}
		return lis;
	}
	
	
	/**
	 * 比较大小 
	 * 如果时间1大于时间2 则返回正数<br>  
	 * 如果时间1等于时间2 则返回0<br>
	 * 如果时间1小于时间2 则返回负数<br>
	 * @param date1 时间1
	 * @param date2 时间2
	 * @return 返回比较后的结果
	 * */
	public static long compare(Date date1,Date date2){
		boolean result;
		return date1.getTime()-date2.getTime();
		
	}
	
	/**
	 * 与N天前时间比较大小
	 * 如果时间大于N天前时间 则返回正数<br>
	 * 如果时间等于N天前时间 则返回0<br>
	 * 如果时间小于N天前时间 则返回负数<br>
	 * @param date 时间
	 * @param ndayAgo n天前的n
	 * @return 返回比较后的结果
	 * */
	public static long compareToNdays(Date date,int ndayAgo){
		Date currentDate=getCurrentDate();
		Date ndaysAgoDate=addTime(currentDate, -ndayAgo, DAY);
		long result=compare(date,ndaysAgoDate);
		return result;
	}
	

	public static String getDefault_timeFormat() {
		return default_timeFormat;
	}

	public static void setDefault_timeFormat(String default_timeFormat) {
		DateUtil.default_timeFormat = default_timeFormat;
	}

	public static String getDefault_timeZone() {
		return default_timeZone;
	}

	public static void setDefault_timeZone(String default_timeZone) {
		DateUtil.default_timeZone = default_timeZone;
	}

	public static String getDefaultTimeformat() {
		return default_timeFormat;
	}

	public static String getDefaultTimezone() {
		return default_timeZone;
	}
	

}
