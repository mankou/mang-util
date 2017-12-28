package mang.util.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.base.CaseFormat;

/**
 * 驼峰处理工具类
 */
public class CamelUtil {

	/**
	 * 获取类的成员变量名 与 转换成下划线分割后 之间的对应关系
	 * 
	 * @param clazz 类名
	 * @return map 转换后的结果
	 * */
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
	 * 获取类的成员变量名转成下划线分割后与成员变量名之间的对应关系
	 * 
	 * @param clazz 类名
	 * @return map 转换后的结果
	 * */
	public static Map<String, String> getLowerUnderscoreMapLowerCamel(Class clazz) {
		Map<String, String> convertMap = new HashMap<String, String>();
		List<String> fieldNameList = FieldUtil.getFieldName(clazz);
		for (String fieldName : fieldNameList) {
			String underScoreName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
			convertMap.put(underScoreName,fieldName);
		}
		return convertMap;
	}
	
	
	/**
	 * 将小写驼峰转换成小写下划线
	 * @param list 原始字符串list
	 * @return List 转换后字符串list
	 * */
	public static List<String> lowerCamel2LowerUnderscoreList(List<String> list){
		List<String> resultList=new ArrayList<String>();
		if(list!=null&&list.size()>0){
			for(String name:list){
				String underScoreName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
				resultList.add(underScoreName);
			}
		}	
		return resultList;
	}
	
	/**
	 * 将小写驼峰转换成小写下划线
	 * @param lowerCamel 原始字符串
	 * @return String 转换后字符串
	 * */
	public static String lowerCamel2LowerUnderscore(String lowerCamel){
		String result=CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, lowerCamel);
		return result;
	}
	
	/**
	 * 将小写下划线转换成小写驼峰
	 * @param list 原始字符串list
	 * @return List 转换后字符串list
	 * */
	public static List<String> lowerUnderscore2LowerCamelList(List<String> list){
		List<String> resultList=new ArrayList<String>();
		if(list!=null&&list.size()>0){
			for(String name:list){
				String underScoreName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
				resultList.add(underScoreName);
			}
		}	
		return resultList;
	}
	
	/**
	 * 将小写下划线转换成小写驼峰
	 * @param lowerUnderscore 原始字符串
	 * @return String 转换后字符串
	 * */
	public static String lowerUnderscore2LowerCamel(String lowerUnderscore){
		String result = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, lowerUnderscore);
		return result;
	}

}
