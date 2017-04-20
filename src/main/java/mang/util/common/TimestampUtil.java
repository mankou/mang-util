package mang.util.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类.
 * @author mang
 * @version
 * create:2015-8-25 15:09:53
 * modify:2015-8-25 15:09:58
 * */
public class TimestampUtil {
	 private final static String default_timeZone="GMT+8";
	 private final static String default_timeFormat = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 获取当前时间.
	 * @return Timestamp
	 * */
	public static Timestamp getCurrentTime(){
		
		//方法一
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        String time = df.format(new Date());   
//        Timestamp now = Timestamp.valueOf(time);
        
        //方法二
		Calendar calendar=Calendar.getInstance();
		Timestamp now = new Timestamp(calendar.getTimeInMillis());
        return now;
	}
	
	
	/**
	 * 给定一个时间和间隔单位 算出下一个时间.
	 * @param time 时间 如果为空 则用当前时间
	 * @param interval 时间间隔
	 * @param unit 时间间隔单位 second:秒    minute:分钟 hour:小时 day:天 month:月 year:年
	 * @return Timestamp
	 * */
	public static Timestamp addTime(Timestamp time,Integer interval,String unit){
		Calendar c = Calendar.getInstance(); //这句好像很浪费，我也不知道该怎么处理
		if(time!=null){
			c.setTime(time);//将Date或Timestamp放进去
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
		
		Timestamp nextRunTime=new Timestamp(c.getTimeInMillis());
		return nextRunTime;
		
	}
	
	/**
	 * 在一个时间上加一个毫秒数 算出下一个时间.
	 * @param time 时间 如果为空则用当前时间
	 * @param millisecond 要相加的毫秒数
	 * @return Timestamp
	 * */
	public static Timestamp addTime(Timestamp time,Integer millisecond){
		Calendar c = Calendar.getInstance(); //这句好像很浪费，我也不知道该怎么处理
		if(time!=null){
			c.setTime(time);//将Date或Timestamp放进去
		}
		c.add(Calendar.MILLISECOND, millisecond);
		Timestamp nextRunTime=new Timestamp(c.getTimeInMillis());
		return nextRunTime;
	}
	
	/**
	 * 获取当前时间的小时 .
	 * <p>如 22:00 则返回22
	 * @param time 时间
	 * @return int
	 * */
	public static int getHour(Timestamp time){
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获取一个时间的0点0分.
	 *<p>获取一个时间的整天时间 如 2015-7-15 10:21:08 则返回 2015-7-15
	 *@param time 时间
	 *@return Timestamp
	 * */
	public static Timestamp getDayWithNoTime(Timestamp time){
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return new Timestamp(c.getTimeInMillis());
	}
	
	/**
	 * 返回当前时间的0点0分
	 * @return Timestamp
	 * */
	public static Timestamp getDayWithNoTime(){
		Timestamp time = TimestampUtil.getCurrentTime();
		return TimestampUtil.getDayWithNoTime(time);
		
	}
	
	
	/**
	 * 计算一个单位的时间是多少毫秒.
	 * 如传入1 second 则返回1000
	 * @param interval 时间间隔
	 * @param unit 时间间隔单位 second:秒   minute:分钟   hour:小时   day:天   month:月  year:年
	 * @return Integer
	 * */
	public static Integer getMillisecond(Integer interval,String unit){
		Integer millisecond=1000;
		if("second".equals(unit)){
			millisecond=millisecond*interval;
		}else if("minute".equals(unit)){
			millisecond=millisecond*interval*60;
		}else if("hour".equals(unit)){
			millisecond=millisecond*interval*60*60;
		}else if("day".equals(unit)){
			millisecond=millisecond*interval*60*60*24;
		}else if("month".equals(unit)){
			millisecond=millisecond*interval*60*60*24*30;
		}else if("year".equals(unit)){
			millisecond=millisecond*interval*60*60*24*365;
		}
		
		return millisecond;
		
	}
	
	/**
	 * 用于计算2个日期间的时间差 并转换成易读的方式 .
	 * <p>endTime-startTime的值，结果是易读的字符串 如 1年5月6天8小时7分5秒
	 * @param endTime 结束时间
	 * @param startTime 开始时间
	 * @param language 语言  en cn english chinese等 分别表示英文、中文
	 * @return String
	 * */
	public static String computeTimeInterval(Timestamp endTime,Timestamp startTime,String language){
		Long between = (endTime.getTime()-startTime.getTime())/1000;//除以1000是为了转换成秒
		between=Math.abs(between); //取绝对值
		
		boolean isEnglish=isEnglish(language);
		
		String secStr=isEnglish?"s":"秒";
		String minStr=isEnglish?"min":"分";
		String houStr=isEnglish?"hour":"小时";
		String dayStr=isEnglish?"day":"天";
		String monStr=isEnglish?"month":"月";
		String yearStr=isEnglish?"year":"年";
		
		long year=between/(365*24*3600);
		long month=between%(365*24*3600)/(30*24*3600);
		long day=between%(30*24*3600)/(24*3600);
		long hour=between%(24*3600)/3600;
		long minute=between%(3600)/60;
		long second=between%(60);
		
		String result="";
		//注 如果是0年 0月 0天 0分 0秒 则返回0秒 而不能是"" 所以秒没有参加if判断
		result+=second+secStr;
		
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
	
	/**
	 * 用于计算2个日期间的时间差 并转换成易读的方式 .
	 * <p>endTime-startTime的值，结果是易读的字符串 如 1year5month6day8hour7min5sec <br>
	 * 默认是英文
	 * @param endTime 结束时间
	 * @param startTime 开始时间
	 * @return String
	 * */
	public static String computeTimeInterval(Timestamp endTime,Timestamp startTime){
		return computeTimeInterval(endTime, startTime, "en");
	}
	
	/**
	 * 计算两个时间的时间差.
	 * <p>endTime.getTime()-startTime.getTime()
	 * @param endTime endTime
	 * @param startTime startTime
	 * @param unit 返回时间差的单位: millisecond  second  min hour
	 * @return Long 返回值是Long型的 只返回整数
	 * */
	public static Long computeTimeLongInterval(Timestamp endTime,Timestamp startTime,String unit){
		Long timeinterval = endTime.getTime()-startTime.getTime();
		Long result;
		if("millisecond".equals(unit)){
			result=timeinterval;
		}else if("second".equals(unit)){
			result=timeinterval/1000;
		}else if("min".equals(unit)){
			result=timeinterval/(1000*60);
		}else if("hour".equals(unit)){
			result=timeinterval/(1000*60*60);
		}else if("day".equals(unit)){
			result=timeinterval/(1000*60*60*24);
		}else{
			result=timeinterval;
		}
		
		return  result;
	}
	
	/**计算两个时间的时间差
	 * <p>time1.getTime()-time2.getTime()
	 * @param time1 time1
	 * @param time2 time2
	 * @param unit 返回时间差的单位: millisecond  second  min hour
	 * @return Long 返回值是Long型的 只返回整数
	 * */
	public static Long computeTimeLongInterval(Timestamp time1,Date time2,String unit){
		return  computeTimeLongInterval(time1,new Timestamp(time2.getTime()),unit);
	}
	
	
	/**
	 * 计算2个时间的时间间隔   time1-time2.
	 * @param unit 单位 hour day week month year
	 * @param time1 time1
	 * @param time2 time2
	 * @param unit 单位
	 * @return Double time1-time2后的数值
	 * */
	public static Double computeTimeDoubleInterval(Timestamp time1, Timestamp time2, String unit) {
		long timeCount = (time1.getTime() - time2.getTime());
		
		long unitTimeLong=1000*60*60;//默认是天
		if("second".equals(unit)){
			unitTimeLong=1000;
		} else if("min".equals(unit)){
			unitTimeLong=1000*60;
		}else if("hour".equals(unit)){
			unitTimeLong=1000*60*60;
		}else if("day".equals(unit)){
			unitTimeLong=1000*60*60*24;
		}else if("week".equals(unit)){
			unitTimeLong=1000*60*60*24*7;
		}else if("month".equals(unit)){
			unitTimeLong=1000*60*60*24*30;
		}else if("year".equals(unit)){
			unitTimeLong=1000*60*60*24*365;
		}
		
		Double totalTaskTime = (double) timeCount/unitTimeLong;

		DecimalFormat df = new DecimalFormat("0.00"); // 取2位小数
		Double interval = new Double(df.format(totalTaskTime).toString());

		return interval;
	}

	/**
	 * 给一个时间 返回时间的字符串   采用默认的时间格式 如time 为 2015-08-01 00:00:00 返回2015-08-01.
	 * 默认的时间格式为yyyy-MM-dd
	 * @param time 时间
	 * @return String 格式化后时间字符串
	 * */
	public static String getDateString(Timestamp time){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String str = sdf.format(time);
		return str;
	}
	
	/**
	 * 给一个时间格式字符串 返回当前时间的字符串
	 * @param format 日期格式  如yyyy-MM-dd yyyyMMddHHmmss
	 * @return String 格式化后的时间字符串
	 * */
	public static String getDateString(String format){
		Timestamp now=getCurrentTime();
		String str=getDateString(now, format);
		return str;
	}
	
	/**
	 * 给一个时间 返回该时间的字符串  
	 * 需自己传入时间格式
	 * @param time 时间
	 * @param format 日期格式  如yyyy-MM-dd yyyyMMddHHmmss
	 * @return String 格式化后的时间字符串
	 * */
	public static String getDateString(Timestamp time,String format){
		if(time==null){
			time=getCurrentTime();
		}
		
		if(format==null||"".equals(format)){
			format="yyyy-MM-dd";
		}
		
		DateFormat sdf = new SimpleDateFormat(format);  
		String str = sdf.format(time);
		return str;
	}
	
	/**
	 * 返回当前时间的时间字符串 .
	 * 需自己传入时间格式
	 * @param format 日期格式  如yyyy-MM-dd yyyyMMddHHmmss
	 * @return String 格式化后的时间字符串
	 * */
	public static String getCurrentDateString(String format){
		Timestamp time = getCurrentTime();
		return getDateString(time,format);
	}
	
	
	private static boolean isEnglish(String language){
		language=language.toLowerCase();
		if("en".equals(language)||"english".equals(language)){
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * 返回两个时间中最小的那个.
	 * 如果有一个为空 则返回另一个。如果两个都为空 则返回空
	 * @param time1 time1
	 * @param time2 time2
	 * @return Timestamp 较小的时间
	 * */
	public static Timestamp min(Timestamp time1,Timestamp time2){
		if(time1==null) return time2;
		
		if(time2==null) return time1;
		
		return time1.getTime()<time2.getTime()? time1:time2;
	}
	
	/**
	 * 返回两个时间中最大的那个.
	 * 如果有一个为空 则返回另一个。如果两个都为空 则返回空
	 * 
	 * @param time1 time1
	 * @param time2 time2
	 * @return Timestamp 较大的时间
	 * */
	public static Timestamp max(Timestamp time1,Timestamp time2){
		if(time1==null) return time2;
		
		if(time2==null) return time1;
		
		return time1.getTime()>time2.getTime()? time1:time2;
	}
	
	/**
	 * 将date 转换成 timestamp类型
	 * @param from 时间
	 * @return Timestamp
	 * */
	public static Timestamp convertDateToTimestamp(Date from){
		Timestamp time=null;
		if(from!=null){
			time=new Timestamp(from.getTime());
		}
		return time;
	}
	
	/**
	 * 将date 转换成 timestamp类型
	 * @param from 时间
	 * @return Date
	 * */
	public static Date convertTimestampToDate(Timestamp from){
		Date date=null;
		if(from!=null){
			date=new Date(from.getTime());
		}
		return date;
	}
	
	/**
	 * 获取某一时间当月的1号 0点的时间
	 * 
	 * */
	public static Timestamp getMonthFirstDay(Timestamp time){
		Calendar c = Calendar.getInstance(); 
		if(time!=null){
			c.setTime(time);//将Date或Timestamp放进去
		}
		
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Timestamp returnTime=new Timestamp(c.getTimeInMillis());
		return returnTime;
		
	}
	
	/**
	 * 获取下个月1号 0点0分0秒的时间.
	 * 
	 * 
	 * */
	public static Timestamp getNextMonthFirstDay(Timestamp time){
		return getFutureMonthFirstDay(time,1);
	}
	
	/**
	 * 获取未来某几个月 的1号0点0分0秒的时间.
	 * 
	 * */
	public static Timestamp getFutureMonthFirstDay(Timestamp time,int addMonth){
		Timestamp nextMonth=addTime(time, 1, "month");
		Timestamp nextMonthFirstDay=getMonthFirstDay(nextMonth);
		return nextMonthFirstDay;
	}
	
	/**
	 * @return Timestamp 当天0点时间
	 * */
	public static Timestamp getCurrentDayZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Timestamp timestamp=new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}
	
	
	/**
	 * @return Timestamp 明天0点
	 * */
	public static Timestamp getTomorrowZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Timestamp timestamp=new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}
	
	
	/**
	 * @return Date 当天0点时间
	 * */
	public static Timestamp getCurrentMonthZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Timestamp timestamp=new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}
	

	/**
	 * @return Date 当天0点时间
	 * */
	public static Timestamp getCurrentYearZeroDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Timestamp timestamp=new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}
	
	/**
	 * 解析时间字符串转换成Timestamp
	 * 
	 * @param timeStr
	 *            时间字符串
	 * @param format
	 *            时间格式 如yyyy-MM-dd HH:mm:ss
	 * @return Timestamp
	 */
	public static Timestamp parse(String timeStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		Timestamp time = null;
		Date date=null;
		try {
			date = df.parse(timeStr);
			time=new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 解析时间字符串转换成Date
	 * 
	 * @param timeStr
	 *            时间字符串 时间格式采用默认的 yyyy-MM-dd HH:mm:ss
	 */
	public static Timestamp parse(String timeStr) {
		return parse(timeStr, default_timeFormat);
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
	 * @return Timestamp
	 */
	public static Timestamp parse(String timeStr, String timeFormat, String timeZone) {
		SimpleDateFormat df = new SimpleDateFormat(timeFormat);
		df.setTimeZone(TimeZone.getTimeZone(timeZone));
		Date date = null;
		Timestamp time=null;
		try {
			date = df.parse(timeStr);
			time=new Timestamp(date.getTime());
			return time;
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
