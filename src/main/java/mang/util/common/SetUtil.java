package mang.util.common;

import java.util.Set;

public class SetUtil {
	
	/**
	 * set to array
	 * @param set set
	 * @return String[]
	 * */
	public static String[] toStringArray(Set<String> set){
		String[] array =set.toArray(new String[set.size()]);
		return array;
	}
}
