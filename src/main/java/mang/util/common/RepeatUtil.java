package mang.util.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 判断重复的工具类
 * */
public class RepeatUtil {
	
	/**
	 * 判断一个list中有无重复的数据.
	 * 
	 * @param lis list
	 * @param clazz list中的类
	 * @param fieldName fieldName 如果类中的该属性值重复就认为是重复
	 * 
	 * @return List 返回list中重复的数据
	 * */
	public static List getRepeat(List lis, Class clazz, String fieldName) {
		List repeatList = new ArrayList<Object>();
		Set set = new HashSet<Object>();
		for (Object obj : lis) {
			Field field;
			try {
				//注 如下这句取不出field 有可能是因为属性是private
//				field = clazz.getField(fieldName);
				field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				Object fieldValue = field.get(obj);
				if (set.contains(fieldValue)) {
					repeatList.add(obj);
				} else {
					set.add(fieldValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return repeatList;
	}
}
