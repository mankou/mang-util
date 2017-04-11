package mang.util.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json处理类.
 * 因JsonUtil中的方法都是静态方法所以提供的功能都很简单不能配置 如时间转换JsonUtil默认替换成毫秒数  所以重新开发一个JsonHandle类可设置时间格式 时区等
 * 
 * */
public class JsonHandle {
	private static Logger logger=Logger.getLogger(JsonHandle.class);
	private  ObjectMapper objectMapper = new ObjectMapper();
	private static String default_timeFormat="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static String default_timeZone="UTC";
	
	public JsonHandle(){
		this.setDateFormater(default_timeFormat);
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
     * Object 转 String
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
