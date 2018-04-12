package mang.util.common;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * 拼接sql语句的公用类.
 * @author mang
 * @version create:2016-09-22 16:07:57 modify:2016-09-22 16:08:03
 * 
 * */
public class SqlUtil {
	
	/**
	 * 拼接带like的sql语句
	 * 如joinLike("bus_name","测试") 则输出  " and bus_name like '%测试%' "
	 * @param property 属性名
	 * @param value 属性值
	 * @return String
	 * */
	public static String joinLike(String property,String value){
		String result="";
		if(NullUtil.isNotNull(value)){
			result= " and "+property+" like "+"'%"+value+"%' ";
		}
		return result;
	}
	
	
	/**
	 * 拼接一般的sql语句
	 * 如joinEqual("bus_name","测试") 则输出  " and bus_name = '测试' "
	 * @param property 属性名
	 * @param value 属性值
	 * @return String
	 * */
	public static String joinEqual(String property,String value){
		String result="";
		if(NullUtil.isNotNull(value)){
			result= " and "+property+" = "+"'"+value+"' ";
		}
		return result;
	}
	
	
	/**
	 * 拼接in查询sql语句
	 * 如joinIn("bus_name","测试,公文") 则输出  " and bus_name in ('测试','公文')"
	 * @param property 属性名
	 * @param value 属性值
	 * @return String
	 * */
	public static String joinIn(String property,String value){
		String result="";
		if(NullUtil.isNotNull(value)){
			String valueTmp=value;
			if (value.contains(",")) {
				//注 这里用另一个变量是怕修改了原始值
				valueTmp = value.replace(",", "','");
			}
			result= " and "+property+" in('" + valueTmp + "') ";
			
		}
		return result;
	}
	
	/**
	 * 拼接in查询sql语句
	 * @param property 属性名
	 * @param inValueList 拼接的值
	 * @return String sql
	 * */
	public static String joinIn(String property,List<String> inValueList){
		if("".equals(property)||property==null||inValueList==null||inValueList.size()==0){
			return "";
		}
		StringBuffer sqlBuffer=new StringBuffer(" "+property+" in " +"( ");
		int size=inValueList.size();
		for(int i=0;i<size;i++){
			String value=inValueList.get(i);
			if(i==0){
				sqlBuffer.append("'"+value+"'");			
			}else{
				sqlBuffer.append(",'"+value+"'");	
			}
		}
		sqlBuffer.append(" ) ");
		String resultSql=sqlBuffer.toString();
		return resultSql;
	}
	
	/**
	 * 处理sql语句 将查询条件按and拼接,值用占位符?代替 
	 * 该方法常用于jdbc式sql语句查询
	 * 
	 * 对于一些查询语句 我只想写前面一部分 后面查询条件那块我不想写 所以写了该类用于处理这些情况
	 * 之所以把值拼接到sql语句中是因为有的值是字符串,有的值不是处理起来比较麻烦
	 * @param sql sql string
	 * @param queryMap queryMap
	 * @return string after process sql string
	 * */
	public static String processAndMark(String sql,LinkedHashMap<String,Object> queryMap){
		StringBuffer sqlBuffer=new StringBuffer(" "+ sql+" ");
		
		Set<String> queryKeys=queryMap.keySet();
		Iterator<String> iter = queryKeys.iterator();
        while(iter.hasNext()){
        	String key = (String)iter.next();
        	sqlBuffer.append(" and "+key+"=?");
        }
		String querySql=sqlBuffer.toString();
		return querySql;
	}
}
