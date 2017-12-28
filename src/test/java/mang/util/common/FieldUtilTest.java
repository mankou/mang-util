package mang.util.common;

import java.util.List;

import org.junit.Test;

public class FieldUtilTest {
	
	@Test
	public void getFieldName(){
		List<String> fieldNameList=FieldUtil.getFieldName(User.class);
		System.out.println(fieldNameList);
	}
}
