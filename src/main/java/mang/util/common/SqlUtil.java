package mang.util.common;

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
}
