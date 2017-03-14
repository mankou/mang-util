package mang.util.doc.poi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

public class WordPoiUtil {
	public static void createWord(String templatePath,String docDestPath,Map<String,String> dataMap){
		try{
			File templeFile = new File(templatePath);
			FileInputStream fileInputStream =new FileInputStream(templeFile);
			
			
			WordTemplate template = new WordTemplate(fileInputStream);
			template.replaceTag(dataMap);
			
			File outFile = new File(docDestPath);
			FileOutputStream out= new FileOutputStream(outFile);
			
			BufferedOutputStream bos = new BufferedOutputStream(out);
			template.write(bos);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
		
	}
}
