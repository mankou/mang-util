package mang.util.common;

import java.util.ArrayList;
import java.util.List;

/**
 * List工具类
 * 
 * @author mang
 */
public class ListUtil {
	/**
	 * 判断一个对象的某一个成员变量的值是否和List中的某一元素的成员变量值相等,只要有一个相等就返回true
	 * @param obj object
	 * @param fieldName fieldName 
	 * @param list list
	 * @return boolean
	 */
	public static boolean equalsAny(Object obj, String fieldName, List list) {
		Object fieldValue = FieldUtil.getFieldValue(obj, fieldName);
		for (Object tmp : list) {
			Object tmpFieldValue = FieldUtil.getFieldValue(tmp, fieldName);

			if (fieldValue == null && tmpFieldValue == null) {
				return true;
			}

			if (fieldValue.equals(tmpFieldValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个字符串是否和List中的某一元素的成员变量值相等,只要有一个相等就返回true
	 * @param str string
	 * @param fieldName field name
	 * @param list list
	 * @return boolean
	 */
	public static boolean equalsAny(String str, String fieldName, List list) {
		for (Object tmp : list) {
			Object tmpFieldValue = FieldUtil.getFieldValue(tmp, fieldName);

			if (str == null && tmpFieldValue == null) {
				return true;
			}

			if (str.equals(tmpFieldValue)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断一个字符串是否和list中的某一元素相等,只要有一个相等就返回true
	 * @param str string
	 * @param list list
	 * @return boolean 
	 */
	public static boolean equalsAny(String str, List<String> list) {
		for (String tmp : list) {
			
			if(str==null && tmp==null){
				return true;
			}
			
			if (str.equals(tmp)) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * 删除list中null元素
     * */
    public static List removeNull(List list){
        List<Object> removeList=new ArrayList<Object>();
        for(Object obj:list){
            if(obj==null){
                removeList.add(obj);
            }
        }
        list.removeAll(removeList);
        return list;
    }
}
