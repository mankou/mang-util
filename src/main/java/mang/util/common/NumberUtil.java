package mang.util.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 数字处理工具包.
 * 
 * @author mang
 * 
 * */
public class NumberUtil {
	/**
	 * double型 数字四舍五入.
	 * 
	 * @param dou 输入的数字
	 * @param scale 精度 如保留2位小数 就写2
	 * @return Double
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
	 * @param l 整型数字
	 * @param scale 精度 如保留2位小数 就写2
	 * @return Double
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
	 * @param str 字符串格式数字
	 * @return boolean 
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
	 * @param num1 数字1
	 * @param num2 数字2
	 * @return Integer
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
	 * @param num1 数字1
	 * @param num2 数字2
	 * @return Integer
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
	 * @param numberStr 字符串格式的数字
	 * @param objClass 转换成数字类型的类 如Long.class
	 * @return Object
	 * */
	public static Object parseNumber(String numberStr, Class objClass) {
		Object obj = null;
		try {
			//有可能传入的字符串有问题导致parseDouble有问题 所以try下
			Double douValue = Double.parseDouble(numberStr);
			if (objClass == int.class || objClass == Integer.class) {
				obj = new Integer(douValue.intValue());
			} else if (objClass == long.class || objClass == Long.class) {
				// obj=Long.parseLong(numberStr);
				obj = new Long(douValue.longValue());
			} else if (objClass == float.class || objClass == Float.class) {
				// obj=Float.parseFloat(numberStr);
				obj = new Float(douValue.floatValue());
			} else if (objClass == double.class || objClass == Double.class) {
				// obj=Double.parseDouble(numberStr);
				obj = douValue;
			} else if (objClass == BigDecimal.class) {
				obj = new BigDecimal(numberStr);
			}
		} catch (Exception e) {

		}

		return obj;
	}
	
	
	/**
	 * 将字符串转换成Double.
	 * 
	 * @param numberStr 字符串数字
	 * @return Double 
	 * */
	public static Double parseDouble(String numberStr){
		Double result=null;
		try{
			result=Double.parseDouble(numberStr);
		}catch (Exception e) {
			
		}
		
		return result;
	}
	
	
	/**
	 * 计算2个Double型值 相减后的值
	 * 
	 * <p>
	 * 如果两个值有一个为空则返回空
	 * </p>
	 * 
	 * @param dou1 Double1
	 * @param dou2 Double2
	 * @return Double 
	 * */
	public static Double subDouble(Double dou1,Double dou2){
		Double result=null;
		if(dou1!=null && dou2!=null){
			result=dou1-dou2;
		}
		return result;
	}
	
	/**
	 * 计算2个Long型值 相减后的值
	 * 
	 * <p>
	 * 如果两个值有一个为空则返回空
	 * </p>
	 * 
	 * @param l1 Long1
	 * @param l2 Long2
	 * @return Long
	 * */
	public static Long subLong(Long l1,Long l2){
		Long result=null;
		if(l1!=null && l2!=null){
			result=l1-l2;
		}
		return result;
	}
	
	/**
	 * 计算2个Integer值 相减后的值
	 * 
	 * <p>
	 * 如果两个值有一个为空则返回空
	 * </p>
	 * 
	 * @param i1 Integer1
	 * @param i2 Integer2
	 * @return Integer
	 * */
	public static Integer subInteger(Integer i1,Integer i2){
		Integer result=null;
		if(i1!=null && i2!=null){
			result=i1-i2;
		}
		return result;
	}
	
	/**
	 * 计算2个double 相加后的值
	 * 
	 * @param d1 d1
	 * @param d2 d2
	 * @return Double
	 * */
	public static Double add(Double d1,Double d2){
		Double result;
		if(d1==null && d2!=null){
			result=d2;
		}else if(d1!=null && d2==null){
			result=d1;
		}else if(d1==null && d2==null){
			result=null;
		}else{
			result=d1+d2;
		}
		return result;
	}
	
	/**
	 * 传入一个整数，返回格式化后的字符串. 如传入8 返回0008
	 * @param l 要格式化的整数
	 * @param precision 精度
	 * */
	public static String format(Long l,int precision){
		DecimalFormat dfInt = new DecimalFormat("00");
		String precisionStr = dfInt.format(precision);
		String format = "%" + precisionStr + "d";
		String result=String.format(format,l);
		return result;
	}
	
	/**
	 * 传入一个整数，返回格式化后的字符串. 如传入8 返回0008
	 * @param l 要格式化的整数
	 * @param precision 精度
	 * */
	public static String format(Integer i,int precision){
		DecimalFormat dfInt = new DecimalFormat("00");
		String precisionStr = dfInt.format(precision);
		String format = "%" + precisionStr + "d";
		String result=String.format(format,i);
		return result;
	}
	
}
