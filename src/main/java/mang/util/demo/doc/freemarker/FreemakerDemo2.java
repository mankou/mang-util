package mang.util.demo.doc.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mang.util.doc.freemarker.WordFreemarkerUtil;


/**
 * 演示 基于freemaker生成带有表格的word
 * */
public class FreemakerDemo2 {

	public static void main(String[] args) {
		String xmlPath=FreemakerDemo2.class.getResource("").getPath()+"word模板-替换表格.xml";
		String docPath="c:/Users/mang/Desktop/testWord/testTable.doc";
		
		//最简单的调用方式
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("userName", "马宁");
		dataMap.put("docTitle", "我的信件");
		dataMap.put("date", "2016-09-29");
		dataMap.put("name", "张三");
		dataMap.put("comment", "亚洲");
		
		List<TableData> tableData=new ArrayList<TableData>();
		tableData.add(new TableData("张三", "甘肃"));
		tableData.add(new TableData("李四", "辽宁"));
		
		dataMap.put("table2", tableData);
		
		
		WordFreemarkerUtil.createWord(xmlPath, docPath, dataMap);
		
		System.out.println("生成文件路径:"+docPath);
		
		//另一种 调用的方式 可以设置更复杂的属性 如 文件重复处理策略等
//		TheFreemarker tf=new TheFreemarker(xmlPath,docPath,dataMap); 
//		tf.setProcessDuplicate(true);
//		tf.setDuplicateProcessStrategy("date");
//		File file = tf.createDoc();
	}
	

}
