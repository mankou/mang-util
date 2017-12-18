package mang.util.os;

/**
 * 操作系统工具类
 * */
public class OSUtil {

	/**
	 * 获取操作系统名称
	 * @return String 操作系统名称
	 */
	public static String getOsName() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	/**
	 * 判断当前操作系统是不是windows
	 * @return boolean 
	 * */
	public static boolean isWindows() {
		String os = getOsName();
		boolean flag=false;
		if (os.toLowerCase().startsWith("win")) {
			flag=true;
		}
		return flag;
	}
}
