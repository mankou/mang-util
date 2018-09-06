package mang.util.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import mang.util.test.entity.Address;
import mang.util.test.entity.User;

public class LowerCamelToUpperUnderscoreStrategyNoGuavaTest {
	
	
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
		mapper.setPropertyNamingStrategy(new LowerCamelToUpperUnderscoreStrategyNoGuava());
		
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
	

}
