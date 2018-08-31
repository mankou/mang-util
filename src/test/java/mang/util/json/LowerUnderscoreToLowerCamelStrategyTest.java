package mang.util.json;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import mang.util.test.entity.UserUnderscore;

public class LowerUnderscoreToLowerCamelStrategyTest {
	
	
	/**
	 * 经测试发现该类只能将user_name 转成userName,而且不论是序列化还是反序列化 其都是通过读类的属性来操作的,估计是读类的属性后经过转换
	 * 然后再根据对应的get/set方法 取值或设置值. 所以你原来理解的互逆是错误的
	 * 
	 * */
	@Test
	public void test() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String json = "{\"userName\":\"bflee\",\"idNumber\":\"123456\"}";
		mapper.setPropertyNamingStrategy(new LowerUnderscoreToLowerCamelStrategy());
		UserUnderscore user = (UserUnderscore) mapper.readValue(json, UserUnderscore.class);
		System.out.println(user.getId_number());
		
		String jsonStr=mapper.writeValueAsString(user);
		System.out.println(jsonStr);
	}
	
	/**
	 * 可以看到其只能将user_name转成userName 而不对将userName转成user_name(转完后还是userName)
	 * 说明你的互逆理论是错误的
	 * */
	@Test
	public void testTranslate(){
		LowerUnderscoreToLowerCamelStrategy trans=new LowerUnderscoreToLowerCamelStrategy();
		String cameStr="userName";
		String underScoreStr="user_name";
		
		String result=trans.translate(cameStr);
		System.out.println(result);
		
		String result2=trans.translate(underScoreStr);
		System.out.println(result2);
		
	}
	

	/**
	 * 主要测试LowerCamelToLowerUnderscoreStrategy 是否正常
	 * 
	 * 其将一个Bean 转换成json格式, 然后再将该json字符串转换成Map类型
	 * 原来的属性名格式是小写下划线式转换后格式为小写驼峰式.
	 * 实际上就是为了试验Bean转换成Map而且属性名格式要由小写驼峰式转换成大写下划线式.
	 * */
	@Test
	public void testBean2Map() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(new LowerUnderscoreToLowerCamelStrategy());
		UserUnderscore userUnderscore=new UserUnderscore();
		userUnderscore.setId_number("001");
		userUnderscore.setUser_name("maning");
		
		String str=mapper.writeValueAsString(userUnderscore);
		System.out.println(str);
		
		Map map=mapper.readValue(str, Map.class);
		System.out.println(map);
		String actUserName=(String) map.get("userName");
		Assert.assertArrayEquals("这2个值应该相等", new Object[]{"maning"}, new Object[]{actUserName});
	}

}
