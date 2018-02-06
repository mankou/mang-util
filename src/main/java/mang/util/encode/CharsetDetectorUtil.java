package mang.util.encode;

public class CharsetDetectorUtil {
	public static String DEFAULT_CHARSET="UTF-8";
	
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
	 * */
	public static String checkCharset(String filePath) {
		return checkCharset(filePath, DEFAULT_CHARSET);
	}
}
