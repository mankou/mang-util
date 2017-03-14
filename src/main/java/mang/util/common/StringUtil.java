package mang.util.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 字符串处理工具类
 * @author mang 
 * @version v1.0 
 * create:2015-8-25 14:25:03 
 * modify:2015-8-25 14:25:05
 */
public class StringUtil {
	/**
	 * 从字符串中提取数字.
	 * <p>如传入m123 返回 123</p>
	 * 
	 * @param str 需要提取数字的字符串
	 * @return string
	 */
	public static String pickUpNumber(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String result = m.replaceAll("").trim();
		return result;

	}
	
	/** 
	 * 将字符串拼接起来.
	 *  <p>将oldStr与newStr拼接起来,分隔符为split
	 * 
	 * @param oldStr 老字符串
	 * @param split 分隔符
	 * @param newStr 新字符串
	 * @return string
	 * @see #join(String, String)
	 */
	public static String join(String oldStr, String split, String newStr) {

//		if (oldStr != null && !"".equals(oldStr)) {
//			oldStr = oldStr + split + newStr;
//		} else {
//			oldStr = newStr;
//		}
//		return oldStr;
		
		if(oldStr==null && newStr==null){
			return null;
		}else if(oldStr==null && newStr!=null){
			return newStr;
		}else if(oldStr!=null && newStr==null){
			return oldStr;
		}else {
			return oldStr+split+newStr;
		}
		
	}
	
	/** 
	 * 接字符串拼接起来.
	 * <p>将oldStr与newStr拼接起来,默认分隔符为逗号","
	 * @param oldStr 老字符串
	 * @param newStr 新字符串
	 * @return String
	 * @see #join(String, String, String)
	 */
	public static String join(String oldStr,String newStr){
		return join(oldStr,",",newStr);
	}
	
	
	/** 
	 * 将字符串拼接起来 并且不重复.
	 *  <p>将oldStr与newStr拼接起来,分隔符为split
	 * @param oldStr 老字符串
	 * @param split 分隔符
	 * @param newStr 新字符串
	 * @return string
	 * @see #join(String, String)
	 */
	public static String joinWithNoRepeat(String oldStr, String split, String newStr) {
		//如果oldStr 包含newStr 则还是返回 oldStr
		if (oldStr != null && !"".equals(oldStr) && (oldStr+split).indexOf(newStr+split)<0) {
			oldStr = oldStr + split + newStr;
		} else {
			oldStr = newStr;
		}
		return oldStr;
	}
	
	/** 
	 * 接字符串拼接起来 并且不重复.
	 * 这个与join的区别在于不重复. 如输入 joinWithNoRepeat("1","1") 还是返回1
	 * <p>将oldStr与newStr拼接起来,默认分隔符为逗号","
	 * @param oldStr 老字符串
	 * @param newStr 新字符串
	 * @return String
	 * @see #join(String, String, String)
	 */
	public static String joinWithNoRepeat(String oldStr,String newStr){
		return joinWithNoRepeat(oldStr,",",newStr);
	}
	
	
	
	
	
	/**
	 * 添加引号,将如下  1,2,3 处理成 '1','2','3'.
	 * <p>一般用于拼接sql
	 * 调用示例 addAuta(str,",","'")
	 * @param str 要处理的字符串
	 * @param split 分隔符
	 * @param replace 替换字符
	 * @return String 处理后的字符串
	 * */
	public static String addQuota(String str,String split,String replace){
		String str1 = str.replaceAll(split, replace+split+replace);
		return replace+str1+replace; 
	}
	
	
	/**
	 * 去掉字符串中的数字.
	 * <p>可去除字符串中的数字，可根据参数location的设置 去除字符串头部、尾部、所有的数字
	 * @param str 需要去除数字的字符串
	 * @param location  
	 * start表示去掉头部的数字 如传入"123中国456" 返回 "中国456"
	 * end 表示去掉尾部的数字   如传入"123中国456"  返回"123中国"
	 * 其它值 去掉全部数字
	 * @return String
	 * 
	 * */
	public static String cleanNumber(String str,String location){
		if("start".equals(location)){
			return str.replaceAll("^\\d+","");
		}else if("end".equals(location)){
			//str.replaceAll("\\d+$",""); 与str.replaceAll("\\d$",""); 效果不一样 前一个删除结尾的所有数字 而后一个只删除结尾的最后一个数字
			return str.replaceAll("\\d+$","");
		}else{
			//20151023经测试str.replaceAll("\\d+","")和str.replaceAll("\\d","");的效果一样
			return str.replaceAll("\\d+","");
		}
		
	}
	
	/**
	 * 选择字符串，如果新字符串不空返回新的字符串.
	 * 如果newStr不是null也不是空字符串，则返回newStr,否则返回oldStr 常用于设置配置 如果配置了用配置的 没有配置用默认的
	 * 
	 * @param oldStr 要处理的字符串
	 * @param newStr 新字符串
	 * @return 选择后的字符串
	 * */
	public static String select(String oldStr,String newStr){
		if(newStr==null||"".equals(newStr)){
			return oldStr;
		}
		return newStr;
	}
	
	/**
	 * 将一个字符串按分隔符转换成数组.
	 *  如1,2,3 转换成字符串数组["1","2","3"]
	 *  如果str为空 则也返回空
	 *  @param str 要处理的字符串
	 *  @param split 分隔符
	 *  @return String[] 转换后的数组
	 * */
	public static String[] splitToArray(String str, String split) {
		if (str != null) {
			String[] strArray = str.split(split);
			return strArray;
		}
		return null;

	}
	
	/**
	 * 拼接sql用. 
	 * 精确查询拼接
	 * @param hql hql语句
	 * @param column 列
	 * @param value 值
	 * @return String 拼接后的sql
	 * */
	public static String joinHql(String hql,String column,String value){
		if(value != null && !"".equals(value)){
			hql += " and "+column+"=" + "'" + value + "' ";
		}
		return hql;
	}
	
	/**
	 * 拼接sql用.拼接like查询
	 * @param hql hql语句
	 * @param column 列名
	 * @param value 值
	 * @param leftLike 左like 如果需要左like就写% 如果不需要就写空字符串
	 * @param rightLike rightLike 如果需要右like就写% 如果不需要就写空字符串
	 * @return hql 返回拼接好的string
	 * */
	public static String joinHqlLike(String hql, String column, String value, String leftLike, String rightLike) {
		leftLike = NullUtil.processNull(leftLike);
		rightLike = NullUtil.processNull(rightLike);
		// 模糊查询
		if (value != null && !"".equals(value)) {
			hql += " and " + column + " like" + "'" + leftLike + value
					+ rightLike + "' ";
		}

		return hql;
	}
	
	/**
	 * 拼接sql用. 拼接in查询
	 * @param hql hql语句
	 * @param column 列名
	 * @param value 值
	 * @return hql 返回拼接好的hql
	 * */
	public static String joinHqlIn(String hql, String column, String value){
		if (value != null && !"".equals(value)) {
			value=StringUtil.addQuota(value, ",", "'");
			hql +=" and " + column + " in ("+value+")";
		}
		
		return hql;
	}
	
	
	/**
	 * 判断用分隔符分隔的字符串是否包含子字符串.
	 * 例1: indexOf("001,002","001",",") 则返回true
	 * 例1: indexOf("001,002","0",",") 则返回false
	 * @param str 要判断的字符串
	 * @param subStr 子字符串
	 * @param split 分隔符
	 * @return boolean true或false
	 * */
	public static boolean indexOf(String str,String subStr,String split){
		if(str==null||"".equals(str)){
			return false;
		}
		if(subStr==null||"".equals(subStr)){
			return false;
		}
		
		boolean flag=false;
		String[] strArray=splitToArray(str, split);
		for(int i=0;i<strArray.length;i++){
			if(subStr.equals(strArray[i])){
				flag=true;
				break;
			}
		}
		return flag;
		
	}
	
	/**
	 * 将用分隔符分隔的字符串 str1 和str2合并 并且不重复.
	 * 如mergWithNoRepeat("001,002","002,003",",") 则返回"001,002,003"
	 * @param str1 str1
	 * @param str2 str2
	 * @param split 分隔符
	 * @return 合并后的字符串
	 * */
	public static String mergeWithNoRepeat(String str1,String str2,String split){
		if(str1==null||"".equals(str1)){ return str2;}
		if(str2==null||"".equals(str2)){ return str1;}
		
		String[] str1Array=str1.split(",");
		String[] str2Array=str2.split(",");
		
		String result="";
		for(int i=0;i<str1Array.length;i++){
			if(!StringUtil.indexOf(result, str1Array[i], ",")){
				result=StringUtil.join(result, ",", str1Array[i]);
			}
		}
		
		for(int i=0;i<str2Array.length;i++){
			if(!StringUtil.indexOf(result, str2Array[i], ",")){
				result=StringUtil.join(result, ",", str2Array[i]);
			}
		}
		
		return result;
	}
	
	/**
	 * 将用分隔符分隔的字符串去重.
	 * 如"001,002,001" 则返回"001,002";
	 * @param str 待处理的字符串
	 * @param split 分隔符
	 * @return String 处理后字符串
	 * */
	public static String removeRepeat(String str,String split){
		if(str==null||"".equals(str)){
			return str;
		}
		
		String result="";
		String[] strArray=str.split(",");
		for(int i=0;i<strArray.length;i++){
			if(!StringUtil.indexOf(result, strArray[i], ",")){
				result=StringUtil.join(result, ",", strArray[i]);
			}
		}
		return result;
	}
	
	/**
	 * 转大写
	 * */
	public static String toUpperCase(String str){
		if(str==null){
			return null;
		}
		
		String result=str.toUpperCase();
		return result;
	}

}
