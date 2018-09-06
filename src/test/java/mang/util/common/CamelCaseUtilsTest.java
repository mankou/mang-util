package mang.util.common;

import org.junit.Assert;
import org.junit.Test;

public class CamelCaseUtilsTest {
	
	@Test
	public void toLowerUnderline(){
		String camelStr="user_name";
		String underLineStr=CamelCaseUtils.toLowerUnderline(camelStr);
		Assert.assertArrayEquals(new String[]{"user_name"}, new String[]{underLineStr});
	}
	
	@Test
	public void toUpperUnderline(){
		String camelStr="user_name";
		String underLineStr=CamelCaseUtils.toUpperUnderline(camelStr);
		Assert.assertArrayEquals(new String[]{"USER_NAME"}, new String[]{underLineStr});
	}
}
