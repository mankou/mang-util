package mang.util.common;

import java.util.Random;

/**
 * 随机数操作类
 * 
 * */
public class RandomUtil {
	
	/**
	 * 
	 * @param baseStr 基准字符串 即随机产生的字符串从哪些字符中产生 如果输入为null或""则默认以a-z字符为基准
	 * @param length 生成字符串的长度
	 * */
	public static String getRandomStr(String baseStr,int length){
		String base;   
		if(baseStr==null || "".equals(baseStr)){
			 base = "abcdefghijklmnopqrstuvwxyz";   
		}else{
			base=baseStr;
		}
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    //length表示生成字符串的长度
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    String str  = sb.toString(); 
	    return str;
	}
	
	/**
	 * 
	 * @param baseStr 基准字符串 即随机产生的字符串从哪些字符中产生 如果输入为null或""则默认以a-z字符为基准
	 * @param length 生成字符串的长度
	 * */
	public static String getRandomStr(int length){
		return getRandomStr("", length);
	}
}
