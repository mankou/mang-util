package mang.util.common;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StrArrayUtilTest {

	@Test 
	public void strIndexArray() {
		String[] array={"60","60万"};
		String str="60万平方米";
		
		boolean flag=StrArrayUtil.strIndexArray(str,array);
		
		assertEquals("值不一样",true, flag);
		
		
		boolean flag2=StrArrayUtil.strIndexArray("70",array);
		assertEquals("值不一样",false, flag2);

	}
	
	@Test
	public void list2Array(){
		List<String> lis=new ArrayList<String>();
		lis.add("a");
		lis.add("b");
		
		String[] array=StrArrayUtil.list2Array(lis);
		
		String[] expectArray=new String[]{"a","b"};
		assertArrayEquals(expectArray, array);
		
	}
	
	@Test
	public void arrayContainStr(){
		String[] array=new String[]{"hello","world"};
		boolean isContain=StrArrayUtil.arrayContainStr("hell", array);
		assertEquals(false, isContain);
		
		
		boolean isContain2=StrArrayUtil.arrayContainStr("hello", array);
		assertEquals(true, isContain2);
	}

}
