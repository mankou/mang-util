package mang.util.json.demo.privateBean;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestPrivateBean {
	
	@Test
	public void testPrivateBean() throws JsonProcessingException{
		PrivateBean bean=new PrivateBean();
		String str=new ObjectMapper().writeValueAsString(bean);
		System.out.println(str);
	}
}
