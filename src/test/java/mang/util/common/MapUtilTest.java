package mang.util.common;


import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class MapUtilTest  {
	
	@Test 
	public void testjava2Map(){
		User user=new User();
		user.setName("ma");
		user.setCode("horse");
		
		Map map= MapUtil.bean2Map(user);
		String name=(String) map.get("name");
		String code=(String) map.get("code");
		
		assertEquals("值不相等", "ma", name);
		assertEquals("值不相等", "horse", code);
	}
}
