package mang.util.common;

import java.math.BigDecimal;

public class FieldUtil {
	public static boolean isNumberClass(Class objClass) {
		boolean isNumber = false;
		if (objClass == int.class || objClass == Integer.class 
			|| objClass == float.class || objClass == Float.class
			|| objClass == long.class || objClass == Long.class 
			|| objClass == double.class|| objClass == Double.class) {
			
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
	
	public static  Object parseNumber(String numberStr,Class objClass){
		Object obj=null;
		if(objClass==int.class || objClass==Integer.class){
			 obj=Integer.parseInt(numberStr);
		}else if(objClass==long.class || objClass==Long.class){
			obj=Long.parseLong(numberStr);
		}else if(objClass==float.class||objClass==Float.class){
			obj=Float.parseFloat(numberStr);
		}else if(objClass==double.class || objClass==Double.class){
			obj=Double.parseDouble(numberStr);
		}else if(objClass==BigDecimal.class){
			obj=new BigDecimal(numberStr);
		}
		
		return obj;
		
	}
}
