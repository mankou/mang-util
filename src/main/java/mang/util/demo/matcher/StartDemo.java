package mang.util.demo.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 示例正则表达式中group的使用
 * */
public class StartDemo {

	public static void main(String[] args) {
		// 原来，group是针对（）来说的，group（0）就是指的整个串，group（1）
		// 指的是第一个括号里的东西，group（2）指的第二个括号里的东西
		String str = "${myname}写一点文字${title}";
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			System.out.println("Group 1:" + matcher.group(1));// 得到第一组匹配——与(or)匹配的
			System.out.println("Start 1:" + matcher.start(1) + " End 1:" + matcher.end(1));// 第一组匹配的索引
		}
	}
	
	//输出结果
	// Group 0:World!
	// Group 1:or
	// Group 2:ld!
	// Start 0:6 End 0:12 //注字符串的索引从0开始
	// Start 1:7 End 1:9
	// Start 2:9 End 2:12
	// Wor

}
