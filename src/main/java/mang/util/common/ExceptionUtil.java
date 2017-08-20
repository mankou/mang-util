package mang.util.common;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	
	
	/**
	 * 将异常信息保存到String变量中
	 * 
	 * @param t 异常
	 * @return String 异常转换成字符串
	 * */
	public static String printStackTraceToString(Throwable t) {
	    StringWriter sw = new StringWriter();
	    t.printStackTrace(new PrintWriter(sw, true));
	    return sw.getBuffer().toString();
	}
}
