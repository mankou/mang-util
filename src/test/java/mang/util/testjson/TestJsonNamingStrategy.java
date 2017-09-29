package mang.util.testjson;

import org.junit.Test;
import mang.util.common.JsonUtil;

public class TestJsonNamingStrategy {
	
	@Test
	public void testPascalCaseStrategy(){
		Request requeyst=new Request();
		String str=JsonUtil.obj2String(requeyst);
		System.out.println(str);
	}
	
	
	@Test
	public void testLowerCaseStrategy(){
		Request2 requeyst=new Request2();
		String str=JsonUtil.obj2String(requeyst);
		System.out.println(str);
	}
	
	
	@Test
	public void testLowerCaseWithUnderscoresStrategy(){
		Request3 requeyst=new Request3();
		String str=JsonUtil.obj2String(requeyst);
		System.out.println(str);
	}
}
