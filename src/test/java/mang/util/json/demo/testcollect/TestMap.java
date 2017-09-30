package mang.util.json.demo.testcollect;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mang.util.json.JsonUtil;

public class TestMap {
	
	@Test
	public void testMap() throws JsonProcessingException{
		Map<String,String> map=new HashMap<String,String>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		
		ObjectMapper objectMapper=new ObjectMapper();
		String str=objectMapper.writeValueAsString(map);
		System.out.println(str);
	}
	
	@Test
	public void testDeserializerMap() throws JsonParseException, JsonMappingException, IOException{
		String str="{\"k1\":\"v1\",\"k2\":\"v2\"}";
		ObjectMapper objectMapper=new ObjectMapper();
		Map<String,String> map=objectMapper.readValue(str, Map.class);
		String v1=map.get("k1");
		
		assertEquals(v1, "v1");
	}
	
	@Test
	public void testMapPojo() throws IOException{
		ObjectMapper objectMapper=new ObjectMapper();
		
		Map<String,Entity> map=new HashMap<String,Entity>();
		Entity e1=new Entity();
		e1.setName("e1name");
		e1.setValue("e1Value");
		
		Entity e2=new Entity();
		e2.setName("e1name");
		e2.setValue("e1Value");
		
		
		map.put("e1", e1);
		map.put("e2", e2);
		
		String str=objectMapper.writeValueAsString(map);
		System.out.println(str);
		
		Map<String,Entity> map2=objectMapper.readValue(str, new TypeReference<Map<String, Entity>>(){});
		
		
		Map<String,Entity> map3=JsonUtil.string2Map(str, String.class, Entity.class);
		
		System.out.println("ha");
		
	}
}
