package mang.util.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * json处理工具类
 * 
 * @author mang
 */

@SuppressWarnings("unchecked")
public class JsonUtil {
	private static Logger logger = Logger.getLogger(JsonUtil.class);

	private static ObjectMapper objectMapper = new ObjectMapper();

	public JsonUtil() {

	}

	static {

		// 如下配置是原来的 不确定是否好用 有的我也不理解具体含义
		// objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,
		// false);
		// objectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING,
		// true);
		// objectMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
		// true);
		// objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		// objectMapper.setFilters(new
		// SimpleFilterProvider().setFailOnUnknownId(false));

		// 如下配置都已测试好用

		// 设置忽略未知属性
		// objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);

		// 设置时间格式
		// DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		// objectMapper.setDateFormat(dateFormat);

		// 空值处理
		// Include.Include.ALWAYS 默认
		// Include.NON_DEFAULT 属性为默认值不序列化
		// Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化
		// Include.NON_NULL 属性为NULL 不序列化
		// objectMapper.setSerializationInclusion(Include.NON_NULL);

	}

	/**
	 * Object 转 String.
	 * 
	 * <p>
	 * 输入参数可以是一般的java类 也可以是Map
	 * 
	 * </p>
	 *
	 * @param src
	 *            str
	 * @return String
	 */
	public static String obj2String(Object src) {
		if (src == null) {
			return null;
		}

		try {
			return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
		} catch (Exception e) {
			logger.error("Parse Object to String error src=" + src, e);
			return null;
		}
	}

	/**
	 * Object 转 byte[]
	 *
	 * @param src
	 *            src
	 * @return byte[]
	 */
	public static byte[] obj2Byte(Object src) {
		if (src == null) {
			return null;
		}

		try {
			return src instanceof byte[] ? (byte[]) src : objectMapper.writeValueAsBytes(src);
		} catch (Exception e) {
			logger.error("Parse Object to byte[] error", e);
			return null;
		}
	}

	/**
     * obj转简单的map 即将object中的属性名当成map的key object中的value当成map的value
     * 
     * @param src obj
     * @return map
     * */
    public static Map obj2SimpleMap(Object src){
    	 if (src == null) {
             return null;
         }
    	 
    	 try{
    		 String str=obj2String(src);
        	 Map map=objectMapper.readValue(str, Map.class);
        	 return map;
    	 }catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	 
    }

	public static <T> T string2Obj(String str, Class<T> clazz) {
		if (str == null || clazz == null) {
			return null;
		}
		str = escapesSpecialChar(str);
		try {
			return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
		} catch (Exception e) {
			// logger.error("Parse String to Object error\nString: {}\nClass<T>:
			// {}\nError: {}", str, clazz.getName(), e);
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
		if (str == null || typeReference == null) {
			return null;
		}
		str = escapesSpecialChar(str);
		try {
			return (T) (typeReference.getType().equals(String.class) ? str
					: objectMapper.readValue(str, typeReference));
		} catch (Exception e) {
			// logger.error("Parse String to Object error\nString:
			// {}\nTypeReference<T>: {}\nError: {}", str,
			// typeReference.getType(), e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将json转ArrayList
	 * 
	 * @param jsonStr
	 *            json格式字符串
	 * @param classOfT
	 *            要转换的类
	 * @return List
	 */
	public static List string2List(String jsonStr, Class classOfT) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, classOfT);
			List lst = objectMapper.readValue(jsonStr, javaType);
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

	/**
	 * 将json字符串转换成Set
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param classOfT Set集合中的类类型           
	 * @return Set
	 */
	public static Set string2Set(String jsonStr, Class classOfT) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Set.class, classOfT);
			Set set = objectMapper.readValue(jsonStr, javaType);
			return set;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json字符串转换成Map
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param keyClass
	 *            key的类型
	 * @param valueClass
	 *            value的类型
	 * @return Map
	 */
	public static Map string2Map(String jsonStr, Class keyClass, Class valueClass) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(HashMap.class, keyClass,
					valueClass);
			Map m = objectMapper.readValue(jsonStr, javaType);
			return m;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T byte2Obj(byte[] bytes, Class<T> clazz) {
		if (bytes == null || clazz == null) {
			return null;
		}
		try {
			return clazz.equals(byte[].class) ? (T) bytes : objectMapper.readValue(bytes, clazz);
		} catch (Exception e) {
			// logger.error("Parse byte[] to Object error\nbyte[]: {}\nClass<T>:
			// {}\nError: {}", bytes, clazz.getName(), e);
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T byte2Obj(byte[] bytes, TypeReference<T> typeReference) {
		if (bytes == null || typeReference == null) {
			return null;
		}
		try {
			return (T) (typeReference.getType().equals(byte[].class) ? bytes
					: objectMapper.readValue(bytes, typeReference));
		} catch (Exception e) {
			// logger.error("Parse byte[] to Object error\nbyte[]:
			// {}\nTypeReference<T>: {}\nError: {}", bytes,
			// typeReference.getType(), e);
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T map2Obj(Map<String, String> map, Class<T> clazz) {
		String str = obj2String(map);
		return string2Obj(str, clazz);
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

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static void setObjectMapper(ObjectMapper objectMapper) {
		JsonUtil.objectMapper = objectMapper;
	}
	
	public static void setCustomObjectMapper(ObjectMapper objectMapper) {
		JsonUtil.objectMapper = objectMapper;
	}
	
	
}
