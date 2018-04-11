package mang.util.common;

import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testParseCompate(){
		String str="2018-04-11";
		Date date=DateUtil.parseCompate(str);
		System.out.println(date);
	}
}
