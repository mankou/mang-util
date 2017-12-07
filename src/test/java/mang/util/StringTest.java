package mang.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringTest {
	
	@Test
	public void test(){
		String str="1||1PMC||M33||20131015||||N";
		
		String[] values=StringUtils.split(str, "||");
		System.out.println(values.length);
	}
	
	@Test
	public void test2(){
		String str="1||1PMC||M33||20131015||||N";
		
		String[] values=str.split("||");
		System.out.println(values.length);
		
		String str2="1|1PMC|M33|20131015||N";
		String[] values2=str2.split("\\|");
		System.out.println(values2);
	}
	
	@Test
	public void test3(){
		String str="1||1PMC||M33||20131015||||N";
		String[] values=StringUtils.splitPreserveAllTokens(str,"||");
		System.out.println(values.length);
	}
	
	@Test
	public void test4(){
		String str="12";
		String outside="\"";
		String result=outside+str+outside;
		System.out.println(result);
	}
	
	@Test
	public void test5(){
		String[] values=new String[]{"1","2","3"};
		for(String value:values){
			value=value+"x";
		}
		
		for(String value:values){
			System.out.println(value);
		}
	}
}
