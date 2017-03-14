/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package mang.util.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * json处理工具类
 * 
 * 20170221来自rocketmq-console工程 我加了str转list的方法
 * 
 * */


@SuppressWarnings("unchecked")
public class JsonUtil {
	private static Logger logger=Logger.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil() {
    }

    static {
//        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
//        objectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
//        objectMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
//        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * Object => String
     *
     * @param src
     * @return
     */
    public static <T> String obj2String(T src) {
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

    /**
     * Object => byte[]
     *
     * @param src
     * @return
     */
    public static <T> byte[] obj2Byte(T src) {
        if (src == null) {
            return null;
        }

        try {
            return src instanceof byte[] ? (byte[]) src : objectMapper.writeValueAsBytes(src);
        }
        catch (Exception e) {
            logger.error("Parse Object to byte[] error", e);
            return null;
        }
    }

    /**
     * String => Object
     *
     * @param str
     * @param clazz
     * @return
     */
    public static <T> T string2Obj(String str, Class<T> clazz) {
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
     * byte[] => Object
     *
     * @param bytes
     * @param clazz
     * @return
     */
    public static <T> T byte2Obj(byte[] bytes, Class<T> clazz) {
        if (bytes == null || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(byte[].class) ? (T) bytes : objectMapper.readValue(bytes, clazz);
        }
        catch (Exception e) {
//            logger.error("Parse byte[] to Object error\nbyte[]: {}\nClass<T>: {}\nError: {}", bytes, clazz.getName(), e);
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * String => Object
     *
     * @param str
     * @param typeReference
     * @return
     */
    public static <T> T string2Obj(String str, TypeReference<T> typeReference) {
        if (str==null || typeReference == null) {
            return null;
        }
        str = escapesSpecialChar(str);
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : objectMapper.readValue(str, typeReference));
        }
        catch (Exception e) {
//            logger.error("Parse String to Object error\nString: {}\nTypeReference<T>: {}\nError: {}", str,
//                typeReference.getType(), e);
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * byte[] => Object
     *
     * @param bytes
     * @param typeReference
     * @return
     */
    public static <T> T byte2Obj(byte[] bytes, TypeReference<T> typeReference) {
        if (bytes == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(byte[].class) ? bytes : objectMapper.readValue(bytes,
                typeReference));
        }
        catch (Exception e) {
//            logger.error("Parse byte[] to Object error\nbyte[]: {}\nTypeReference<T>: {}\nError: {}", bytes,
//                typeReference.getType(), e);
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
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
    
    
    /**
	 * 将json转ArrayList
	 * */
	public static List string2List(String jsonStr,Class classOfT){
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
