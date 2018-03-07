package mang.util.encode;

import mang.util.common.StringUtil;

public class CharsetDetectorUtil {
	private static String DEFAULT_CHARSET="UTF-8";
	
	/**
	 * 判断文件编码
	 * @param filePath 文件路径
	 * @param defaultCharset 如果没有判断出文件编码 则给出一个默认的
	 * */
	public static String checkCharset(String filePath, String defaultCharset) {
		
		/*
		 * 实际上用CharsetDetector.checkCharset 就可以了,但我不想在代码里判断空的情况所以再加一个工具类
		 * */
		
		// 先判断文件编码
		String encode = CharsetDetector.checkCharset(filePath);
		if (encode == null || "".equals(encode)) {
			encode = defaultCharset;
		}
		return encode;
	}
	
	
	/**
	 * 判断文件编码
	 * @param filePath 文件路径
	 * @param defaultCharset 如果没有判断出文件编码 则给出一个默认的
	 * @param smartModify 是否自动替换
	 * */
	public static String checkCharset(String filePath, String defaultCharset,boolean smartModify) {
		String encode=checkCharset(filePath, defaultCharset);
		if(smartModify){
			//smartModify if detect encode is GB2312 then replace of GB18030, because GB18030 > GB1212
			encode=StringUtil.equalsReplace(encode, "GB2312", "GB18030");
		}
		return encode;
	}
	
	
	/**
	 * 判断文件编码
	 * @param filePath 文件路径
	 * @param smartModify 自动替换策略 因有时文件编码是GB2312 但实际编码可能是GB18030所以替换下,因为GB18030的字符集大于GB2312
	 * */
	public static String checkCharset(String filePath,boolean smartModify) {
		return checkCharset(filePath, DEFAULT_CHARSET, smartModify);
	}
	
	
	/**
	 * 判断文件编码
	 * @param filePath 文件路径
	 * */
	public static String checkCharset(String filePath) {
		return checkCharset(filePath, DEFAULT_CHARSET);
	}
}
