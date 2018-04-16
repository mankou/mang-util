package mang.util.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class CamelUtilTest {

	@Test
	public void getLowerCamelMapLowerUnderscore(){
		Map<String,String> convertMap=CamelUtil.getLowerCamelMapLowerUnderscore(User.class);
		System.out.println(convertMap);
	}
	
	@Test
	public void getLowerUnderscoreMapLowerCamel(){
		Map<String,String> convertMap=CamelUtil.getLowerUnderscoreMapLowerCamel(User.class);
		System.out.println(convertMap);
	}
	
	@Test
	public void lowerCamel2LowerUnderscore(){
		String str="userName";
		String dest="user_name";
		String result=CamelUtil.lowerCamel2LowerUnderscore(str);
		System.out.println(result);
		Assert.assertArrayEquals(new String[]{dest}, new String[]{result});
	}
	
	@Test
	public void lowerUnderscore2LowerCamel(){
		String str="user_name";
		String dest="userName";
		String result=CamelUtil.lowerUnderscore2LowerCamel(str);
		System.out.println(result);
		Assert.assertArrayEquals(new String[]{dest}, new String[]{result});
	}
	
	@Test
	public void lowerCamel2LowerUnderscoreList(){
		String str[]=new String[]{"userName","userCode","helloWorld"};
		String dest[]=new String[]{"user_name","user_code","hello_world"};
		List<String> strList= Arrays.asList(str);
		List<String> resultList=CamelUtil.lowerCamel2LowerUnderscoreList(strList);
		System.out.println(resultList);
		Assert.assertArrayEquals(dest, resultList.toArray());
	}
	
	@Test
	public void lowerUnderscore2LowerCamelList(){
		String str[]=new String[]{"user_name","user_code","hello_world"};
		String dest[]=new String[]{"userName","userCode","helloWorld"};
		List<String> strList= Arrays.asList(str);
		List<String> resultList=CamelUtil.lowerUnderscore2LowerCamelList(strList);
		System.out.println(resultList);
		Assert.assertArrayEquals(dest, resultList.toArray());
	}
	
	@Test
	public void underscore2FirstCharUpperUnderscore(){
		String str="user_name";
		String result=CamelUtil.underscore2FirstCharUpperUnderscore(str);
		Assert.assertArrayEquals(new String[]{"User_Name"},new String[]{result});
	}
	
	@Test
	public void underscore2FirstCharUpperUnderscoreList(){
		String str[]=new String[]{"user_name","user_code"};
		String dest[]=new String[]{"User_Name","User_Code"};
		List<String> strList= Arrays.asList(str);
		List<String> resultList=CamelUtil.underscore2FirstCharUpperUnderscore(strList);
		Assert.assertArrayEquals(dest,resultList.toArray());
	}
	
	@Test
	public void lowerCamel2FirstCharUpperUnderscore(){
		String str="userName";
		String result=CamelUtil.lowerCamel2FirstCharUpperUnderscore(str);
		Assert.assertArrayEquals(new String[]{"User_Name"},new String[]{result});
	}
	
	@Test
	public void lowerCamel2UpperCamel(){
		String str="userName";
		String result=CamelUtil.lowerCamel2UpperCamel(str);
		Assert.assertArrayEquals(new String[]{"UserName"},new String[]{result});
	}
	
	@Test
	public void lowerCamel2UpperUnderscore(){
		String str="userName";
		String result=CamelUtil.lowerCamel2UpperUnderscore(str);
		Assert.assertArrayEquals(new String[]{"USER_NAME"},new String[]{result});
	}
	
}
