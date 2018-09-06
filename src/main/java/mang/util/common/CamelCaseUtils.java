package mang.util.common;

/**
 * 驼峰处理类
 * */
public class CamelCaseUtils {
	/*
	 * 背景:本来已经有一个驼峰处理类 其使用了guava,但因私服仓库中没有guava,所以想弄另一个驼峰类来替代guava
	 * */
	
	
	private static final char SEPARATOR = '_';

	
	/**
	 * 转换成小写下划线式
	 * */
	public static String toLowerUnderline(String s) {
		if (s == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}
	
	/**
	 * 转换成大写下划线式
	 * */
	public static String toUpperUnderline(String str){
		if(str==null) return str;  //garbage in garbage out
		String underlineStr=toLowerUnderline(str);
		return underlineStr.toUpperCase();
	}
	

	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
}
