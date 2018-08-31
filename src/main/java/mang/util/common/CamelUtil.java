package mang.util.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Ascii;
import com.google.common.base.CaseFormat;

/**
 * 驼峰处理工具类
 */
public class CamelUtil {
	
	/**
	 * 获取类的成员变量名(驼峰式) 与 转换成小写的下划线分割后 之间的对应关系.
	 * key是类的成员变量名 value是转换成下划线格式后的值
	 * @param clazz
	 *            类名
	 * @return map 转换后的结果
	 */
	public static Map<String, String> getLowerCamelMapLowerUnderscore(Class clazz) {
		Map<String, String> convertMap = new HashMap<String, String>();
		List<String> fieldNameList = FieldUtil.getFieldName(clazz);
		for (String fieldName : fieldNameList) {
			String underScoreName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
			convertMap.put(fieldName, underScoreName);
		}
		return convertMap;
	}

	/**
	 * 获取类的成员变量名转成小写下划线分割后与成员变量名之间的对应关系.
	 * key 是转换成小写下划线格式的值  value是类的成员变量名
	 * 
	 * @param clazz
	 *            类名
	 * @return map 转换后的结果
	 */
	public static Map<String, String> getLowerUnderscoreMapLowerCamel(Class clazz) {
		Map<String, String> convertMap = new HashMap<String, String>();
		List<String> fieldNameList = FieldUtil.getFieldName(clazz);
		for (String fieldName : fieldNameList) {
			String underScoreName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
			convertMap.put(underScoreName, fieldName);
		}
		return convertMap;
	}


	/**
	 * 将小写驼峰转换成小写下划线.
	 * 其入参是一个string  如将userName 转换成user_name
	 * @param lowerCamel
	 *            原始字符串
	 * @return String 转换后字符串
	 */
	public static String lowerCamel2LowerUnderscore(String lowerCamel) {
		String result = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, lowerCamel);
		return result;
	}
	
	/**
	 * 将小写驼峰转换成小写下划线.
	 * 其入参是一个list
	 * @param list
	 *            原始字符串list
	 * @return List 转换后字符串list
	 */
	public static List<String> lowerCamel2LowerUnderscoreList(List<String> list) {
		List<String> resultList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (String name : list) {
				String underScoreName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
				resultList.add(underScoreName);
			}
		}
		return resultList;
	}
	
	
	/**
	 * 
	 * 将小写驼峰格式转换成大写下划线形式.
	 * 如将userName 转换成USER_NAME
	 * @param lowerCamel 小写驼峰格式字符串
	 * @return 转换后的字符串
	 * */
	public static String lowerCamel2UpperUnderscore(String lowerCamel) {
		String upperUnderscore=CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, lowerCamel);
		return upperUnderscore;
	}
	
	/**
	 * 将小写驼峰转换成首字母大写的下划线风格
	 * 如将userName 转换成 User_Name
	 * 
	 * @param lowerCamel
	 *            原始字符串 其是小写驼峰格式
	 * @return String 转换后的字符串
	 */
	public static String lowerCamel2FirstCharUpperUnderscore(String lowerCamel) {
		String lowerUnderscore = lowerCamel2LowerUnderscore(lowerCamel);
		String firstCharUpperUnderscore = underscore2FirstCharUpperUnderscore(lowerUnderscore);
		return firstCharUpperUnderscore;
	}
	

	/**
	 * 将小写驼峰转换成大写驼峰式.
	 * 如将userName 转换成 UserName
	 */
	public static String lowerCamel2UpperCamel(String lowerCamel) {
		String upperCamel = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, lowerCamel);
		return upperCamel;
	}
	


	/**
	 * 将小写下划线转换成小写驼峰.
	 * 其入参是一个String 如将user_name 转换成userName
	 * 
	 * @param lowerUnderscore
	 *            原始字符串
	 * @return String 转换后字符串
	 */
	public static String lowerUnderscore2LowerCamel(String lowerUnderscore) {
		String result = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, lowerUnderscore);
		return result;
	}
	
	
	/**
	 * 将大写下划线转换成小写驼峰.
	 * 入将USER_NAME 转换成userName
	 * 
	 * @param upperUnderscore 大写下划线格式字符串
	 * 
	 * @return 返回小写驼峰式字符串
	 * */
	public static String upperUnderscore2LowerCamel(String  upperUnderscore){
		String result=CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, upperUnderscore);
		return result;
	}
	

	/**
	 * 将小写下划线转换成小写驼峰. 
	 * 入参是一个list
	 * 
	 * @param list
	 *            原始字符串list
	 * @return List 转换后字符串list
	 */
	public static List<String> lowerUnderscore2LowerCamelList(List<String> list) {
		List<String> resultList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (String name : list) {
				String underScoreName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
				resultList.add(underScoreName);
			}
		}
		return resultList;
	}


	/**
	 * 将小写下划线式转换成首字母大写的下划线风格 如 user_name 变成User_Name;
	 * 
	 * @param underscore
	 *            原始字符串 其是下划线分割的 如user_name 也可以是大写 USER_NAME
	 * @return String 转换后字符串
	 */
	public static String underscore2FirstCharUpperUnderscore(String underscore) {
		String[] wordArray = underscore.split("_");

		List<String> list = new ArrayList<String>();
		for (String word : wordArray) {
			String tmp = Ascii.toUpperCase(word.charAt(0)) + Ascii.toLowerCase(word.substring(1));
			list.add(tmp);
		}

		String result = "";
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				result += list.get(i) + "_";
			} else {
				result += list.get(i);
			}
		}
		return result;
	}

	/**
	 * 将小写下划线转换成首字母大写的下划线风格 但入参是一个list
	 * 
	 * @param list
	 *            原始字符串list
	 * @return List 转换后字符串list
	 */
	public static List<String> underscore2FirstCharUpperUnderscore(List<String> list) {
		List<String> resultList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (String name : list) {
				String firstCharUpperUnderScoreName = underscore2FirstCharUpperUnderscore(name);
				resultList.add(firstCharUpperUnderScoreName);
			}
		}
		return resultList;
	}

}
