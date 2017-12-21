package mang.util.common;

import java.math.BigDecimal;

/**
 * 空值处理工具包.
 * @author mang
 * @version 
 * create:2015-10-10 09:09:02
 * modify:2015-10-10 09:09:04
 * */
public class NullUtil {
	/**
	 * 对字符串进行空值处理.
	 * <p>如果输入为null 则返回"" 否则返回原值
	 * @param str str
	 * @return String
	 * */
	public static String processNull(String str){
		if(str==null){
			return "";
		}
		return str;
	}
	
	/**
	 * 对double型进行空值处理.
	 * <p>如果输入为null 则返回0D 否则回原值
	 * @param dou double类型
	 * @return Double 
	 * 
	 * */
	public static Double processNull(Double dou){
		if(dou==null){
			return 0D;
		}
		return dou;
	}
	
	/**
	 * 对BigDecimal型进行空值处理.
	 * <p>如果输入为null 则返回0 否则返回原值
	 * @param big BigDecimal类型
	 * @return BigDecimal
	 * 
	 * */
	public static BigDecimal processNull(BigDecimal big){
		if(big==null){
			return new BigDecimal(0D);
		}
		return big;
	}
	
	/**
	 * 判断字符串是否为空.
	 * @param str 字符串
	 * @return boolean 为空返回true 不为空返回false
	 * */
	public static boolean isNull(String str){
		if(str==null||"".equals(str)){
			return true;
		}
		return false;
		
	}
	
	/**
	 * 判断字符串是否为非空.
	 * @param str 字符串
	 * @return boolean 非空返回true 空返回false
	 * */
	public static boolean isNotNull(String str){
		return !isNull(str);
	}
	
	
	/**
	 * 如果为null则使用默认值
	 * @param obj 在判断的值
	 * @param defaultValue 默认值
	 * @return Object 
	 * */
	public static Object defaultValues(Object obj,Object defaultValue){
		if(obj==null){
			return defaultValue;
		}else{
			return obj;
		}
	}

}
