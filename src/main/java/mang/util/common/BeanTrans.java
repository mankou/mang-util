package mang.util.common;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * map 与bean 转换的工具类
 * 
 * @author : mang
 */
public class BeanTrans {
	private static final Logger log = LoggerFactory.getLogger(BeanTrans.class);

	/**
	 * Map 转 Bean
	 */
	public static Object map2bean(Map map, Class clazz) {
		try{
			Object o = clazz.newInstance();
			BeanUtils.populate(o, map);
			return o;
		}catch (Exception e) {
			log.error("trans map to bean error",e);
			return null;
		}
		
	}

	/**
	 * List<Map> 转List<Bean>
	 */
	public static List listMap2ListBean(List list, Class clazz) {
		List newList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map tempMap = (Map) list.get(i);
			try {
				Object o = clazz.newInstance();
				BeanUtils.populate(o, tempMap);
				newList.add(o);
			} catch (Exception e) {
				log.error("trans error"+e);
			} 
		}
		return newList;
	}

}
