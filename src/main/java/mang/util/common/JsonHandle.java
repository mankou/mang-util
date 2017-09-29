package mang.util.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * json处理类.
 * 因JsonUtil中的方法都是静态方法所以提供的功能都很简单不能配置 如时间转换JsonUtil默认替换成毫秒数  所以重新开发一个JsonHandle类可设置时间格式 时区等
 * 
 * @author mang
 * 
 * */
public class JsonHandle {
	private static Logger logger=Logger.getLogger(JsonHandle.class);
	private  ObjectMapper objectMapper = new ObjectMapper();
	private static String default_timeFormat="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static String default_timeZone="UTC";
	
	
	public JsonHandle() {
		this.setDateFormater(default_timeFormat);
	}
	
	
	/**
	 * 设置json空值处理策略
	 * 如果为默认值 则不序列化该属性
	 * 如果为空 则不序列化该属性
	 * 如果为空或者 空字符串则不序列化该属性
	 * 如果为空 则处理成空字符串
	 * */
	public void setProcessNullStrategy(JsonNullStrategy strategy) {
		if (JsonNullStrategy.NON_DEFAULT.equals(strategy)) {
			objectMapper.setSerializationInclusion(Include.NON_DEFAULT);
		} else if (JsonNullStrategy.NON_NULL.equals(strategy)) {
			objectMapper.setSerializationInclusion(Include.NON_NULL);
		} else if (JsonNullStrategy.NON_EMPTY.equals(strategy)) {
			objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		} else if (JsonNullStrategy.EMPTY_STRING.equals(strategy)) {
			objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
				@Override
				public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
						throws IOException, JsonProcessingException {
					jg.writeString("");
				}
			});
		}
	}
	
	/**
	 * 设置属性转换方式.
	 * */
	public void setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy){
		objectMapper.setPropertyNamingStrategy(propertyNamingStrategy);
	}
	
	
	
	/**
	 * 
	 * @param  formater 时间格式  如"yyyy-MM-dd HH:mm:ss"
	 * */
	public void setDateFormater(String formater){
		this.setDateFormater(formater,default_timeZone);
	}
	
	
	/**
	 * @param formater 时间格式字符串
	 * @param timeZone 时区 如GMT+8 这里默认时间是UTC
	 * */
	public void setDateFormater(String formater,String timeZone){
		DateFormat dateFormat=new SimpleDateFormat(formater);
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		objectMapper.setDateFormat(dateFormat);
	}
	
	
	 /**
     * Object 转 String.
     * 
     * <p>
     * 输入参数可以是一般的java类
     * 也可以是Map
     * </p>
     * 
     * @param src str
     * @return String
     */
    public  String obj2String(Object src) {
        if (src == null) {
            return null;
        }

        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        }
        catch (Exception e) {
            logger.error("Parse Object to String error src=" + src, e);
            return null;
        }
    }
    
    public  <T> T string2Obj(String str, Class<T> clazz) {
        if (str==null || clazz == null) {
            return null;
        }
        str = escapesSpecialChar(str);
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        }
        catch (Exception e) {
//            logger.error("Parse String to Object error\nString: {}\nClass<T>: {}\nError: {}", str, clazz.getName(), e);
        	e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Escapes Special Character
     *
     * @param str
     * @return
     */
    private static String escapesSpecialChar(String str) {
        return str.replace("\n", "\\n").replace("\r", "\\r");
    }
}
