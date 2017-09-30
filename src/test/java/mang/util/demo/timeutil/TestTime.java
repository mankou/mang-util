package mang.util.demo.timeutil;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestTime {

	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
//		System.out.println(calendar.getTimeInMillis());
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		
		Date date1=calendar.getTime();
		
		calendar.setTimeZone(TimeZone.getTimeZone("GMT-8"));
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		Date date2=calendar.getTime();
//		System.out.println(calendar.getTimeInMillis());
		System.out.println("haa");
	}

}
