package mang.util.json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class JsonUtil2 {
	/**
	 * 将json字符串转换成类
	 * */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		T bean=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
//			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//			mapper.setDateFormat(fmt);
			UTCDateDeserializer utcDateDeserializer = new UTCDateDeserializer();  
	        SimpleModule newModule = new SimpleModule("UTCDateDeserializer", PackageVersion.VERSION);  
	        newModule.addDeserializer(Date.class, utcDateDeserializer);  
	        mapper.registerModule(newModule);  
			bean = mapper.readValue(json, classOfT);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bean;
    }
	
	/**
	 * 将类转换成json字符串
	 * */
	public static  String toJson(Object obj){
		String jsonStr=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			mapper.setDateFormat(fmt);
			jsonStr=mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
}
