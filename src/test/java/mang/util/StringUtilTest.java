package mang.util;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import junit.framework.AssertionFailedError;
public class StringUtilTest {
	
	@Test
	public void testDefault(){
		String result;
		
		String str1="    ";		
		result=StringUtils.defaultIfBlank(str1, "123");
		System.out.println("=============");
		System.out.println("原始字符串:"+str1);
		System.out.println("处理后:"+result);
		
		
		String str2=null;		
		result=StringUtils.defaultIfBlank(str2, "123");
		System.out.println("=============");
		System.out.println("原始字符串:"+str2);
		System.out.println("处理后:"+result);
		
		
		String str3="";		
		result=StringUtils.defaultIfBlank(str3, "123");
		System.out.println("=============");
		System.out.println("原始字符串:"+str3);
		System.out.println("处理后:"+result);
	}
	
	@Test
	public void testSubString(){
		String str="abcdefg";
		String result=StringUtils.substring(str, 0, 6);
		System.out.println(result);
		
		String result2=StringUtils.substring(str, 0, 10);
		System.out.println(result2);
		
		//下面报错
		String result3=str.substring(0, 10);
		System.out.println(result3);
	}

}
