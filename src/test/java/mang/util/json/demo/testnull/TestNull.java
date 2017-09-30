package mang.util.json.demo.testnull;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mang.util.json.NullStringSerialize;

public class TestNull {
	
	@Test
	public void testNull() throws JsonProcessingException{
		//通过mappe设置全局的空值处理策略
		MyDto mydto=new MyDto();
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		String str=objectMapper.writeValueAsString(mydto);
		System.out.println(str);
	}
	
	
	@Test
	public void testNON_NULL() throws JsonProcessingException{
		//如果是空值null 则序列化后的json不包含该属性
		MyDtoNonNull mydto=new MyDtoNonNull();
		String str=new ObjectMapper().writeValueAsString(mydto);
		System.out.println(str);
	}
	
	
	@Test
	public void testNON_NULL2() throws JsonProcessingException{
		//如果是空值null 则序列化后的json不包含该字符串
		MyDtoNonNull mydto=new MyDtoNonNull();
		mydto.setStringValue("");
		String str=new ObjectMapper().writeValueAsString(mydto);
		System.out.println(str);
	}
	
	@Test
	public void testNON_EMPTY() throws JsonProcessingException{
		//如果是空值null 或者是空字符串   则序列化后的json不包含该属性
		MyDtoNonEmpty mydto=new MyDtoNonEmpty();
		mydto.setStringValue("");
		String str=new ObjectMapper().writeValueAsString(mydto);
		System.out.println(str);
		
		MyDtoNonEmpty mydto2=new MyDtoNonEmpty();
		String str2=new ObjectMapper().writeValueAsString(mydto2);
		System.out.println(str2);
	}
	
	@Test
	public void testNON_DEFAULT() throws JsonProcessingException{
		//如果属性是默认值 则序列化后的json不包含该属性
		MyDtoNonDefault mydto=new MyDtoNonDefault();
		String str=new ObjectMapper().writeValueAsString(mydto);
		System.out.println(str);
		
		MyDtoNonDefault mydto2=new MyDtoNonDefault();
		mydto2.setStringValue("XX");
		mydto2.setIntValue(3);
		String str2=new ObjectMapper().writeValueAsString(mydto2);
		System.out.println(str2);
	}
	
	@Test
	public void testNullString() throws JsonProcessingException{
		//如果属性是null 则返回空字符串
		MyDto mydto=new MyDto();
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.getSerializerProvider().setNullValueSerializer(new NullStringSerialize());
		String str=objectMapper.writeValueAsString(mydto);
		System.out.println(str);
	}
}
