package mang.util.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class BeanTransTest {
	
	@Test
	public void map2bean(){
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("name", "张三");
		m.put("code", "003");
		
		User user=(User) BeanTrans.map2bean(m, User.class);
		
		Assert.assertArrayEquals(new String[]{"张三","003"}, new String[]{user.getName(),user.getCode()});
	}
	
	@Test
	public void listMap2ListBean(){
		List<Map> mapList=new ArrayList<Map>();
		Map<String,Object> m1=new HashMap<String,Object>();
		m1.put("name", "张三");
		m1.put("code", "003");
		
		Map<String,Object> m2=new HashMap<String,Object>();
		m2.put("name", "张四");
		m2.put("code", "004");
		
		mapList.add(m1);
		mapList.add(m2);
		
		List<User> userList=BeanTrans.listMap2ListBean(mapList, User.class);
		
		Assert.assertNotNull(userList);
		Assert.assertArrayEquals(new String[]{"张三","003"}, new String[]{userList.get(0).getName(),userList.get(0).getCode()});
	}

}
