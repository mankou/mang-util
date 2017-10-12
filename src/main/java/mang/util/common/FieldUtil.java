package mang.util.common;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class FieldUtil {
	public static boolean isNumberClass(Class objClass) {
		boolean isNumber = false;
		if (objClass == int.class || objClass == Integer.class || objClass == float.class || objClass == Float.class
				|| objClass == long.class || objClass == Long.class || objClass == double.class
				|| objClass == Double.class) {

			isNumber = true;
		}
		return isNumber;
	}

	public static boolean isBooleanClass(Class objClass) {
		boolean isBoolean = false;
		if (objClass == boolean.class || objClass == Boolean.class) {
			isBoolean = true;
		}
		return isBoolean;
	}

	public static Object parseNumber(String numberStr, Class objClass) {
		Object obj = null;
		if (objClass == int.class || objClass == Integer.class) {
			obj = Integer.parseInt(numberStr);
		} else if (objClass == long.class || objClass == Long.class) {
			obj = Long.parseLong(numberStr);
		} else if (objClass == float.class || objClass == Float.class) {
			obj = Float.parseFloat(numberStr);
		} else if (objClass == double.class || objClass == Double.class) {
			obj = Double.parseDouble(numberStr);
		} else if (objClass == BigDecimal.class) {
			obj = new BigDecimal(numberStr);
		} else if (objClass == String.class) {
			obj = numberStr;
		}

		return obj;

	}

	/**
	 * 用反射的方式获取某一类实例的属性值. 注 该方法只能获取当前类中显示定义的属性 对于父类中的属性值不能获取
	 * 
	 * @param obj
	 *            类实例
	 * @param fieldName
	 *            属性名
	 */
	public static Object getFieldValue(Object obj, String fieldName) {

		Object result = null;
		try {
			Field f = obj.getClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			result = f.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 用反射的方式设置某一实例的属性值
	 * 
	 * @param obj 实例
	 * @param fieldName 属性名
	 * @param fieldValue 属性值
	 * */
	public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
		Field targetField;
		try {
			targetField = obj.getClass().getDeclaredField(fieldName);
			targetField.setAccessible(true);
			targetField.set(obj, fieldValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 如果属性值为空 则用反射的方式设置某一实例的属性值
	 * 
	 * @param obj
	 *            实例
	 * @param fieldName
	 *            属性名
	 * @param fieldValue
	 *            属性值
	 * */
	public static void setFieldValueIfNull(Object obj, String fieldName, Object fieldValue) {
		Field targetField;
		try {
			Object targetFieldValue=getFieldValue(obj, fieldName);
			if(targetFieldValue==null){
				targetField = obj.getClass().getDeclaredField(fieldName);
					targetField.setAccessible(true);
					targetField.set(obj, fieldValue);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 用反射的方式获取某一类实例的父类中的属性值.
	 * 
	 * @param obj
	 *            类实例
	 * @param fieldName
	 *            属性名
	 */
	public static Object getSuperClassFieldValue(Object obj, String fieldName) {

		Object result = null;
		try {
			Field f = obj.getClass().getSuperclass().getDeclaredField(fieldName);
			f.setAccessible(true);
			result = f.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
