package mang.util.common;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	public static String[] weeks_cn = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	public static String[] weeks_cn_short = {"日","一","二","三","四","五","六"};
	public static String[] weeks_num = {"7","1","2","3","4","5","6"};
	public static String[] weeks_en = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	public static String[] weeks_en_short = {"Sun","Mon","Tues","Wed","Thu","Fri","Sat"};
	
	public static String getDayOfWeek(Date time){
       return getDayOfWeek(time, weeks_cn);  
	}
	
	public static String getDayOfWeek(Date time,String[] weekArry){
        Calendar cal = Calendar.getInstance();  
        cal.setTime(time);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;  
        if(week_index<0){  
            week_index = 0;  
        }   
        return weekArry[week_index];  
	}
}
