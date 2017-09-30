package mang.util.json.demo.jsonorder;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestOrder {
	
	@Test
	public void testOrder() throws JsonProcessingException{
		MyBean bean=new MyBean(2, "name");
		String result = new ObjectMapper().writeValueAsString(bean);
		System.out.println(result);
		
	}
}
