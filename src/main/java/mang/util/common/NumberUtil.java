package mang.util.common;

import java.math.BigDecimal;

/**
 * 数字处理工具包.
 * 
 * */
public class NumberUtil {
	/**
	 * double型 数字四舍五入.
	 * 
	 * @param dou 输入的数字
	 * @param scale 精度 如保留2位小数 就写2
	 * 
	 * */
	public static Double round(Double dou,int scale){
			if(dou==null){
				return 0D;
			}
		BigDecimal   b   =   new   BigDecimal(dou);  
		dou   =   b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();  //四舍五入 取2位小数
		return dou;
	}
	
	/** 
	 * 将long型数字 转换成double 并保留几位小数.
	 * @param scale 精度 如保留2位小数 就写2
	 * */
	public static Double round(long l,int scale){
		double dou = l;
		BigDecimal   b   =   new   BigDecimal(dou);  
		dou   =   b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();  //四舍五入 取2位小数
		return dou;
	}
	
	
	/**
	 * 判断字符串是否是数字.
	 * 如传入123则返回true，传入 123中 则返回false
	 * */
	public static boolean isNumber(String str){
		if(str==null){
			return false;
		}
		String tmp = str.replaceAll("\\d", "");
		if("".equals(tmp)){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 比较两个整型数据哪个大? 返回最大的那个.
	 * 如果num1为空 num2不空 则返回num2 如果num2也为空则返回空 反之
	 * */
	public static Integer max(Integer num1,Integer num2){
		if(num1==null) {
			return num2;
		}else if(num2==null){
			return num1;
		}
		
		return num1>num2?num1:num2;
	}
	
	/**
	 * 比较两个整型数据哪个大? 返回最大的那个.
	 * 如果两个数中有一个为空 则返回空
	 * */
	public static Integer min(Integer num1,Integer num2){
		if(num1==null) {
			return num1;
		}else if(num2==null){
			return num2;
		}
		return num1<num2?num1:num2;
	}
	
	/**
	 * 将字符串数字解析成数字
	 * */
	public static  Object parseNumber(String numberStr,Class objClass){
		Object obj=null;
		Double douValue=Double.parseDouble(numberStr);
		if(objClass==int.class || objClass==Integer.class){
			 obj=new Integer(douValue.intValue());
		}else if(objClass==long.class || objClass==Long.class){
//			obj=Long.parseLong(numberStr);
			 obj=new Long(douValue.longValue());
		}else if(objClass==float.class||objClass==Float.class){
//			obj=Float.parseFloat(numberStr);
			obj=new Float(douValue.floatValue());
		}else if(objClass==double.class || objClass==Double.class){
//			obj=Double.parseDouble(numberStr);
			obj=douValue;
		}else if(objClass==BigDecimal.class){
			obj=new BigDecimal(numberStr);
		}
		
		return obj;
	}
	
	
}
