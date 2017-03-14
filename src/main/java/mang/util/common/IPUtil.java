package mang.util.common;

import java.net.InetAddress;

/**
 * ip类工具类.
 * 
 * */
public class IPUtil {
	 
		/**
		 * 获取当前ip.
		 * 
		 * */
	    public static String getIP() {  
	    	try{
	    		 String ip = InetAddress.getLocalHost().getHostAddress();  
	  	       return ip;
	    	}catch (Exception e) {
				return "error";
			}
	      
	    }  
	  
}
