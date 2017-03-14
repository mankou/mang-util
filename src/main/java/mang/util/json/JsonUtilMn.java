package mang.util.json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


/**
 * json工具类
 * @author mang
 * */
public class JsonUtilMn {
	/**
	 * 将json字符串转换成类
	 * */
	public static <T> T fromJson(String json, Class<T> classOfT) {
		T bean=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
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
			jsonStr=mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	/**
	 * 将json转ArrayList
	 * */
	public static List json2List(String jsonStr,Class classOfT){
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, classOfT);
			List lst =  mapper.readValue(jsonStr, javaType);
			return lst;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}