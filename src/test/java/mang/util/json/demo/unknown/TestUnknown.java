package mang.util.json.demo.unknown;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUnknown {

	@Test
	public void testUnknown() throws IOException {

		// 配置全局的忽略不认识的属性
		ObjectMapper objectmapper = new ObjectMapper();

		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String str = "{\"stringValue\":\"1\",\"intValue\":1,\"booleanValue\":true,\"stringValue2\":\"2\"}";

		MyDto entity = objectmapper.readValue(str, MyDto.class);
		System.out.println(entity);
	}

	@Test
	public void testIgnoreUnknownProperty() throws JsonParseException, JsonMappingException, IOException {
		// 配置全局的忽略不认识的属性
		ObjectMapper objectmapper = new ObjectMapper();

		objectmapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String str = "{\"stringValue\":\"1\",\"intValue\":1,\"booleanValue\":true,\"stringValue2\":\"2\"}";

		MyDtoJsonIgnoreUnknown entity = objectmapper.readValue(str, MyDtoJsonIgnoreUnknown.class);
		System.out.println(entity);
	}
}
