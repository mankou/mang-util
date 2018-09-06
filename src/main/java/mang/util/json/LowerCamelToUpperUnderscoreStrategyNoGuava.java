package mang.util.json;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;

import mang.util.common.CamelCaseUtils;

/**
 * json属性转换类: 类的属性名是小写驼峰式如userName  json格式是大写下划线式 如USER_NAME.
 * 这里不依赖guava进行格式转换 因为有的私服中可能没有guava的jar包
 * */
public class LowerCamelToUpperUnderscoreStrategyNoGuava extends PropertyNamingStrategyBase{
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String translate(String input) {
		if (input == null)
			return input; // garbage in, garbage out
		
		
		//小写驼峰式转大写下划线式
		String result=CamelCaseUtils.toUpperUnderline(input);
		return result;
	}

}
