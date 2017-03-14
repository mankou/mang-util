package mang.util.doc.freemarker;

import java.io.File;
import java.util.Map;

/**
 * 用freemarker的方式生成word的工具类.
 * 
 * @see FreemakerDemo1  FreemakerDemo2
 * 
 * */
public class WordFreemarkerUtil {
	
	/**
	 * 生成word
	 * 
	 * @param xmlTemplatePath xml模板绝对路径
	 * @param docDestPath 生成word的绝对路径
	 * @param dataMap 替换word中变量的数据
	 * */
	public static File createWord(String xmlTemplatePath,String docDestPath,Map<String,Object> dataMap){
		//生成word
		FreemarkerHandler tf=new FreemarkerHandler(xmlTemplatePath,docDestPath,dataMap); 
		File file = tf.createDoc();
		return file;
	}
	
	
}
