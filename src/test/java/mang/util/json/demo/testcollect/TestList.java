package mang.util.json.demo.testcollect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestList {
	
	@Test
	public void testList() throws IOException{
		List<String> lis=new ArrayList<String>();
		lis.add("a");
		lis.add("b");
		lis.add("c");
		
		ObjectMapper objectMapper=new ObjectMapper();
		
		String str=objectMapper.writeValueAsString(lis);
		System.out.println(str);
		
		List<String> lis2=objectMapper.readValue(str, List.class);
		System.out.println(lis2);
		
	}
	
	
	@Test
	public void testListBean() throws IOException{
		List<Entity> lis=new ArrayList<Entity>();
		Entity e1=new Entity();
		e1.setName("e1name");
		e1.setValue("e1value");
		
		Entity e2=new Entity();
		e2.setName("e2name");
		e2.setValue("e2value");
		
		Entity e3=new Entity();
		e3.setName("e3name");
		e3.setValue("e3value");
		
		lis.add(e1);
		lis.add(e2);
		lis.add(e3);
		
		ObjectMapper objectMapper=new ObjectMapper();
		String str=objectMapper.writeValueAsString(lis);
		System.out.println(str);
		
		
		List<Entity> lis2 = objectMapper.readValue(str, new TypeReference<List<Entity>>() {}); 
		System.out.println(lis2);
		
		
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Entity.class); 
		List<Entity> lis3 = objectMapper.readValue(str,javaType); 
		System.out.println(lis3);
		
	}
}
