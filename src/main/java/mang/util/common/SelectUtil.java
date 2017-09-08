package mang.util.common;

public class SelectUtil {
	
	/**
	 * 如果原值为空 则使用默认值
	 * @param value 原值
	 * @param defaultValue 默认值
	 * @return 返回double
	 * */
	public static Double select(Double value,Double defaultValue){
		if(value==null){
			return defaultValue;
		}
		return value;
	}
	
	
	/**
	 * 如果原值为空 则使用默认值
	 * @param value 原值
	 * @param defaultValue 默认值
	 * @return 返回String
	 * */
	public static String select(String value,String defaultValue){
		if(value==null || "".equals(value)){
			return defaultValue;
		}
		return value;
	}
}
