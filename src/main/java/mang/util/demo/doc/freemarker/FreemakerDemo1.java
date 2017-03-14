package mang.util.demo.doc.freemarker;

import java.util.HashMap;
import java.util.Map;

import mang.util.doc.freemarker.WordFreemarkerUtil;

/**
 * 演示 基于freemaker生成word
 * */
public class FreemakerDemo1 {

	public static void main(String[] args) {
		String xmlPath=FreemakerDemo1.class.getResource("").getPath()+"word模板-替换变量.xml";
		String docPath="c:/Users/mang/Desktop/testWord/test.doc";
		
		//最简单的调用方式
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userName", "马宁");
		dataMap.put("docTitle", "我的信件");
		dataMap.put("date", "2016-09-29");
		
		WordFreemarkerUtil.createWord(xmlPath, docPath, dataMap);
		
		System.out.println("生成文件路径:"+docPath);
		
		//另一种 调用的方式 可以设置更复杂的属性 如 文件重复处理策略等
//		TheFreemarker tf=new TheFreemarker(xmlPath,docPath,dataMap); 
//		tf.setProcessDuplicate(true);
//		tf.setDuplicateProcessStrategy("date");
//		File file = tf.createDoc();
	}

}
