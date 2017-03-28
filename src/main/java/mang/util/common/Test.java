package mang.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Test {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date t = new Date();
		System.out.println(df1.format(t));
		System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
		df1.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println(df1.format(t));
		System.out.println("-----------");
		System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
		System.out.println("2014-08-27T18:02:59.676Z");
	}

}
