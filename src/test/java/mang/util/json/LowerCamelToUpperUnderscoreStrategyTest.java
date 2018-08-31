package mang.util.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import mang.util.test.entity.Address;
import mang.util.test.entity.User;

public class LowerCamelToUpperUnderscoreStrategyTest {
	
	/**
	 * 主要测试LowerCamelToUpperUnderscoreStrategy 是否正常
	 * 
	 * 其将一个Bean 转换成json格式, 然后再将该json字符串转换成Map类型
	 * 原来bean中属性名格式是小写驼峰式转换成map后key的格式为大写下划线式.
	 * 实际上就是为了试验Bean转换成Map而且属性名格式要由小写驼峰式转换成大写下划线式.
	 * */
	@Test
	public void testBean2Map() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(new LowerCamelToUpperUnderscoreStrategy());
		User userCame=new User();
		userCame.setUserCode("001");
		userCame.setUserName("maning");
		
		Address address=new Address();
		address.setAddressName("中华路");
		
		Address address2=new Address();
		address2.setAddressName("华中路");
		
		userCame.addAddress(address);
		userCame.addAddress(address2);

		
		String str=mapper.writeValueAsString(userCame);
		System.out.println(str);
		
		Map map=mapper.readValue(str, Map.class);
		System.out.println(map);
		
		String actUserCode=(String) map.get("USER_CODE");
		
		Assert.assertArrayEquals("这2个值应该相等", new Object[]{"001"}, new Object[]{actUserCode});
	}
	
	/**
	 * 主要测试LowerCamelToUpperUnderscoreStrategy 是否正常
	 * 
	 * 其将一个List<Bean> 转换成json格式, 然后再将该json字符串转换成List<Map>类型
	 * 原来的属性名格式是小写驼峰式转换后格式为大写下划线式.
	 * 实际上就是为了试验将List<Bean> 转换成List<Map> 而且属性名格式要由小写驼峰式转换成大写下划线式.
	 * */
	@Test
	public void testListBean2ListMap() throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(new LowerCamelToUpperUnderscoreStrategy());
		
		User user1=new User();
		user1.setUserCode("001");
		user1.setUserName("maning");
		Address address=new Address();
		address.setAddressName("中华路");
		Address address2=new Address();
		address2.setAddressName("华中路");
		user1.addAddress(address);
		user1.addAddress(address2);
		
		User user2=new User();
		user2.setUserCode("001");
		user2.setUserName("maning");
		Address address3=new Address();
		address3.setAddressName("中华路");
		Address address4=new Address();
		address4.setAddressName("华中路");
		user2.addAddress(address3);
		user2.addAddress(address4);
		
		List<User> lis=new ArrayList<User>();
		lis.add(user1);
		lis.add(user2);
		String str=mapper.writeValueAsString(lis);
		System.out.println(str);
		List<Map> mapList=mapper.readValue(str,List.class);
		
		String actUserCode=(String) mapList.get(0).get("USER_CODE");
		
		Assert.assertArrayEquals("这2个值应该相等", new String[]{"001"}, new String[]{actUserCode});
		
	}
	
	/**
	 * 主要测试其是否能将小写驼峰式转换成大写下划线式  如userName 转成USER_NAME
	 * */
	@Test
	public void testTranslate(){
		String userName="userName";
		LowerCamelToUpperUnderscoreStrategy trans=new LowerCamelToUpperUnderscoreStrategy();
		String userNameTrans=trans.translate(userName);
		Assert.assertArrayEquals(new String[]{"USER_NAME"}, new String[]{userNameTrans});
	}

}
