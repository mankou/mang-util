package mang.util.json.demo.testcollect;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSet {
	
	@Test
	public void testSet() throws IOException{
		Set<String> set=new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		
		ObjectMapper objectMapper=new ObjectMapper();
		String str=objectMapper.writeValueAsString(set);
		System.out.println(str);
		
		
		Set<String> set2=objectMapper.readValue(str, Set.class);
		System.out.println(set2);
	}
	
	@Test
	public void testSetBean() throws IOException{
		Entity e1=new Entity();
		e1.setName("e1name");
		e1.setValue("e1value");
		
		Entity e2=new Entity();
		e2.setName("e2name");
		e2.setValue("e2value");
		
		Entity e3=new Entity();
		e3.setName("e3name");
		e3.setValue("e3value");
		
		
		Set<Entity> set=new HashSet<Entity>();
		set.add(e1);
		set.add(e2);
		set.add(e3);
		ObjectMapper objectMapper=new ObjectMapper();
		String str=objectMapper.writeValueAsString(set);
		
		System.out.println(str);
		
		
		Set<Entity> set2 = objectMapper.readValue(str, new TypeReference<Set<Entity>>() {});
		System.out.println(set2);
		
		
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Set.class, Entity.class); 
		Set<Entity> set3 = objectMapper.readValue(str,javaType);
		System.out.println(set3);
		
	}
	
	
}
