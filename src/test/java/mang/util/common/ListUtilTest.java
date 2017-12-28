package mang.util.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ListUtilTest {
	
	@Test
	public void equalsAny(){
		User user=new User();
		user.setCode("001");
		user.setName("maning");
		
		
		User user1=new User();
		user1.setCode("001");
		user1.setName("yangzhong");
		
		User user2=new User();
		user2.setCode("002");
		user2.setName("ningmeng");
		
		List<User> testList=new ArrayList<User>();
		testList.add(user1);
		testList.add(user2);
		
		boolean expectTrue=ListUtil.equalsAny(user, "code", testList);
		System.out.println(expectTrue);
		
		
		Assert.assertTrue(expectTrue);
		
		List<User> testList2=new ArrayList<User>();
		testList2.add(user2);
		boolean expectFalse=ListUtil.equalsAny(user, "code", testList2);
		
		System.out.println(expectFalse);
		Assert.assertFalse(expectFalse);
		
		
		
	}
}
