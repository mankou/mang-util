package mang.util.common;

import java.util.List;

/**
 * 与字符串数组有关的工具类.
 * */
public class StrArrayUtil {

	/**
	 * 判断字符串str 是否包含  字符串数组array中的某一个字符串  
	 * 
	 * @param str 字符串
	 * @param array 字符串数组
	 * @return boolean 如果该字符串包含array中的任一元素则返回true, 否则返回false
	 * */
	public static boolean strIndexArray(String str,String[] array){
		boolean flag=false;
		if(array!=null &&str!=null){
			for(String strVal:array){
				if(str.indexOf(strVal)>-1){
					flag=true;
					break;
				}
			}
		}
		return flag;
	}
	
	
	/**
	 * 判断字符串是否在array中
	 * 
	 * @param str 字符串
	 * @param array 字符串数组
	 * @return 如果array包含str 则返回true 否则返回false
	 * */
	public static boolean arrayContainStr(String str,String[] array){
		boolean flag=false;
		if(array!=null && str!=null){
			for(String strVal:array){
				if(str.equals(strVal)){
					flag=true;
					break;
				}
			}
		}
		return flag;
	}
	
	
	/**
	 * 将List 转换成Array数组
	 * @param list 字符串list
	 * @return 字符串数组
	 * */
	public static String[] list2Array(List<String> list){
		
		if(list==null){
			return null;
		}
		
		String[] strArray= new String[list.size()];
		list.toArray(strArray);
		return strArray;
		
	}
	
}
