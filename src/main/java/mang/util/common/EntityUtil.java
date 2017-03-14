package mang.util.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 实体类工具类.
 * */
public class EntityUtil {
	private static Logger logger=Logger.getLogger(EntityUtil.class);

	/**
	 * 获取两个实例相同的属性名.
	 * @return 返回两个实例相同的属性名组成的字符串数组
	 * */
	public static String[] getSameProperty(Object src,Object target){
		Field[] srcFields=src.getClass().getDeclaredFields();
		
		Field[] targetFields=target.getClass().getDeclaredFields();
		
		List<String> samePropertyList=new ArrayList<String>();
		for(Field field1:srcFields){
			for(Field field2:targetFields){
				if(field1.getName().equals(field2.getName())){
					samePropertyList.add(field1.getName());
					break;
				}
			}
		}
		
		//如下将List<String> 转换成array 至于为什么要加一个newString[0] 我也不明白 反正加了就不报错了
		String[] samePropertyArray=(String[])samePropertyList.toArray(new String[0]);
		return samePropertyArray;
		
	}
	
	/**
	 * 给一个实体的某一属性设置属性值.
	 * 
	 * @param obj 实体
	 * @param propertyName 属性名
	 * @param value 属性值
	 * */
	public static void setEntityValue(Object obj,String propertyName,Object value){
		try{
			Field srcField = obj.getClass().getDeclaredField(propertyName);
			srcField.setAccessible(true); //设置些属性是可以访问的  因有时属性是private的 如果不设置会报错
			srcField.set(obj, value);
		}catch (Exception e) {
			logger.warn("属性不存在:"+propertyName);
		}
	}
	
	/**
	 * 给一个实体的一组属性设置属性值.
	 * 
	 * @param obj 实体
	 * @param propertyNameArr 属性名
	 * @param valueList 属性值 注属性值要与属性名一一对应
	 * */
	public static void setEntityValue(Object obj,String[] propertyNameArr,List<Object> valueList){
		for(int i=0;i<propertyNameArr.length;i++){
			String propertyName=propertyNameArr[i];
			Object value=valueList.get(i);
			setEntityValue(obj, propertyName, value);
		}
	}
}
