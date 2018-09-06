package mang.util.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import mang.util.test.entity.UserWithJsonProperty;

public class JsonUtilTest {
	
	@Test
	public void testList(){
		
		UserWithJsonProperty user1=new UserWithJsonProperty();
		user1.setUserCode("001");
		user1.setUserName("maning");
		
		UserWithJsonProperty user2=new UserWithJsonProperty();
		user2.setUserCode("002");
		user2.setUserName("maning2");
		
		List<UserWithJsonProperty> userList=new ArrayList<UserWithJsonProperty>();
		userList.add(user1);
		userList.add(user2);
		
		String jsonStr=JsonUtil.obj2String(userList);
		System.out.println(jsonStr);
		
		List<UserWithJsonProperty> userList2=JsonUtil.string2List(jsonStr, UserWithJsonProperty.class);
		System.out.println(userList2);
		
	}

}
