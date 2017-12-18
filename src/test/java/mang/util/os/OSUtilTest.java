package mang.util.os;

import org.junit.Test;

public class OSUtilTest {
	
	@Test
	public void testIsWindows(){
		boolean isWindows=OSUtil.isWindows();
		System.out.println("当前操作系统是windows么?"+isWindows);
	}
	
	@Test
	public void testGetOsName(){
		String os=OSUtil.getOsName();
		System.out.println(os);
	}
}
