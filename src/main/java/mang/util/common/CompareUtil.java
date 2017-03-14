package mang.util.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较工具类
 * @author mang
 * @version 
 * create:2015-10-9 17:31:34
 * modify:2015-10-9 17:31:37
 */
public class CompareUtil {
	/**
	 * 对比oldList 与 newList 对比出哪些是addList 哪些是deleteList.
	 * <p>需要自己实现equals()方法,要求如果两个对象相同 则equals()返回true 否则返回false
	 * @param oldList 旧list
	 * @param newList 新list
	 * @return List 返回的结果是List<List> 
	 * <br>resultList.get(0) 为delelist
	 * <br>resultList.get(1) 为addlist
	 * 
	 * */
	public static List checkHisList(List oldList,
			List newList) {
		List deleteList = new ArrayList();
		List addList = new ArrayList();
		List<List> retList = new ArrayList();

		for (int i = 0; i < newList.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < oldList.size(); j++) {
				if (oldList.get(j).equals(newList.get(i))) {
					flag = false;
					break;
				}
			}
			if (flag) {
				addList.add(newList.get(i));
			}
		}

		for (int i = 0; i < oldList.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < newList.size(); j++) {
				if (oldList.get(i).equals(newList.get(j))) {
					flag = false;
					break;
				}
			}
			if (flag) {
				deleteList.add(oldList.get(i));
			}
		}

		retList.add(0, deleteList);
		retList.add(1, addList);

		return retList;
	}
	
}
