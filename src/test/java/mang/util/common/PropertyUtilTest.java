package mang.util.common;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class PropertyUtilTest {
	
	@Test
	public void getPriorValueTest(){
		String value=PropertyUtil.getPriorValue("config/test.properties", "test");
		System.out.println(value);
		assertArrayEquals(new String[]{"XXX"}, new String[]{value});
	}
}
