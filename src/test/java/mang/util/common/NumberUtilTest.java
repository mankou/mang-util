package mang.util.common;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class NumberUtilTest {

	@Test
	public void testFormatLong(){
		Long l=10L;
		String sn=NumberUtil.format(l, 4);
		assertArrayEquals("testFormatLong ok", new String[]{"0010"}, new String[]{sn});
	}
	
	@Test
	public void testFormatInteger(){
		Integer i=10;
		String sn=NumberUtil.format(i, 4);
		assertArrayEquals("testFormatInteger ok", new String[]{"0010"}, new String[]{sn});
	}
}
