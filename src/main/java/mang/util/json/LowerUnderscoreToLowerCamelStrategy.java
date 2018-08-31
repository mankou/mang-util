package mang.util.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

/**
 * json属性转换类   类的属性名是小写下划线式如user_name, json的格式是小写驼峰式userName
 * 如类属性名为user_name json格式是userName, 这种转换有点反常但有时确实有这样的需求.
 * */
public class LowerUnderscoreToLowerCamelStrategy extends PropertyNamingStrategyBase{
	
	private static final long serialVersionUID = 1L;
	
	
	//该方法神奇的地方就在于 其既可以将小写驼峰式转小写下划线式,也可以将小写下划线式转小写驼峰. 其是互逆的  
	/*
	 * 该类不是我自己写的,从网上抄的
	 * 来自于 https://blog.csdn.net/maoliangfei2011/article/details/52796662  JSON 驼峰转下划线
	 * 但具体代码我没有看参透
	 * 
	 * 但是20180830 测试时 其只能将小写下划线式转成小写驼峰式,但不能将小写驼峰式转换后仍然是小写驼峰式
	 * */
	
	@Override
	public String translate(String input) {
		if (input == null)
			return input; // garbage in, garbage out
		int length = input.length();
		StringBuilder result = new StringBuilder(length * 2);
		int resultLength = 0;
		boolean wasPrevTranslated = false;
		for (int i = 0; i < length; i++) {
			char c = input.charAt(i);
			
			if (i > 0 || c != '_') // skip first starting underscore
			{
				if (c == '_') {
					if (!wasPrevTranslated && resultLength > 0 && !Character.isUpperCase(result.charAt(resultLength - 1))) {
						if (++i < length) {
							result.append(Character.toUpperCase(input.charAt(i)));
							resultLength++;
						}
					}
					wasPrevTranslated = true;
				} else {
					wasPrevTranslated = false;
					result.append(c);
					resultLength++;
				}
			}
		}
		return resultLength > 0 ? result.toString() : input;
	}

}
