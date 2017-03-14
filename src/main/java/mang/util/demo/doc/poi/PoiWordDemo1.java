package mang.util.demo.doc.poi;

import java.util.HashMap;
import java.util.Map;

import mang.util.doc.poi.WordPoiUtil;

public class PoiWordDemo1 {

	public static void main(String[] args) {
		String DOCX_MODEL_PATH =PoiWordDemo1.class.getResource("").getPath()+"word模板1.docx";
		String DOCX_FILE_WRITE = "c:/Users/mang/Desktop/testWord/replaceModelWord.docx";
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("userName", "马宁");
		map.put("level", "绝密");
		map.put("date", "2015-09-26");
		map.put("docTitle", "我的申请");
//		map.put("htmlStr", "<p>20160926164055测试</p>");
		map.put("approveInfo", "通过");
		
		
		WordPoiUtil.createWord(DOCX_MODEL_PATH, DOCX_FILE_WRITE, map);

	}

}
