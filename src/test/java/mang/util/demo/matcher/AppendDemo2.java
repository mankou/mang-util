package mang.util.demo.matcher;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 演示正则表达式matcher的appendReplacement和appendTail()的使用.
 * 这里想达到如下的目的 
 * 要处理的字符串如下 ${username}${title}alalalala 
 * 我有一个map用于将变量替换成指定的字符串，但如果我的map中没有包含该变量则不替换。如map中有 username 马宁 没有title
 * 则替换后中 马宁${title}alalalala 
 * */
public class AppendDemo2 {
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", "马宁");
		
		String str="${username}${title}alalalala";
		String runText="";
		
		StringBuffer sb = new StringBuffer(); 
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
//			runText=matcher.replaceAll("1");
			String matcherStr=matcher.group(1);
			System.out.println("group:"+matcher.group()+"\t group0:"+matcher.group(0)+"\t group1:"+matcherStr);
			if(params.get(matcherStr)!=null){
				matcher.appendReplacement(sb, (String) params.get(matcherStr)); 
			}
			
		}
		
		matcher.appendTail(sb); 
		System.out.println("最后的结果:"+sb.toString());
	}
		
}
