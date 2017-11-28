package mang.util.common;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MD5 {
	
	/**
	 * 这是原来pszx王健生成MD5的代码 这里摘过来
	 * */
	private static String MD5Str(String sourceStr) throws Exception {
	    try {
	    	String result = "";
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(sourceStr.getBytes());
	      byte b[] = md.digest();
	      int i;
	      StringBuffer buf = new StringBuffer("");
	      for (int offset = 0; offset < b.length; offset++) {
	        i = b[offset];
	        if (i < 0)
	          i += 256;
	        if (i < 16)
	          buf.append("0");
	        buf.append(Integer.toHexString(i));
	      }
	      result = buf.toString();
	      return result;

	    } catch (Exception e) {
	      throw new Exception("字符串异常!",e);
	    }
	  }
	
	private static String replaceStr(String value) throws Exception{
		try {
			value = value.replaceAll("a", "v");
	    	value = value.replaceAll("b", "i");
	    	value = value.replaceAll("c", "o");
	    	value = value.replaceAll("d", "z");
	    	value = value.replaceAll("e", "y");
	    	value = value.replaceAll("f", "t");
			return value;
		} catch (Exception e) {
			throw new Exception("替换字符串异常!",e);
		}
		
	}
	
	public static String MD5ToPassword(String plate,String time) {
		if(plate==null || plate.trim().equals("") || time==null || time.trim().equals(""))
//			throw new Exception("加密关键字不能为空!");
			throw new RuntimeException("不能为空!");
		try {
			//取出车牌号的中文字符
	    	String reg = "[\u4e00-\u9fa5]";
	    	Pattern pat = Pattern.compile(reg);  
	    	Matcher mat=pat.matcher(plate); 
	    	String plateStr = mat.replaceAll("");
//	    	System.out.println("去除中文车牌号后:"+plateStr);
	    	String str = time+plateStr;
//	    	System.out.println("待加密字符串："+str);
	    	String pwd = MD5Str(str);
	    	pwd = replaceStr(pwd);
	    	
	    	//获取当前时间
	    	Date date=new Date();
	    	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String temp_time=formatter.format(date);
	    	
	    	
	    	StringBuffer sb = new StringBuffer(MD5Str(temp_time));
	    	sb.insert(8, pwd);
//	    	System.out.println("加密后字符串："+sb.toString());
	    	return sb.toString();
		} catch (Exception e) {
//			throw new Exception("获取字符串加密异常!",e);
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 校验密码是否正确.
	 * @param s1 字符串1
	 * @param s2 字符串2
	 * @return boolean 
	 * */
	public static boolean check(String s1,String s2){
		try{
			//如果有一个为空就false
			if(s1==null || s2==null){
				return false;
			}
			
			String s18=s1.substring(8, 39);
			String s28=s2.substring(8, 39);
			
			if(s18.equals(s28)){
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			return false;
		}
		
		
	}
	
}
