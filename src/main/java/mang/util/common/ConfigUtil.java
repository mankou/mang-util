package mang.util.common;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 取配置文件工具类 配置文件外置化时常会用到.
 */
public class ConfigUtil {

	/**
	 * 优先从工作空间取 如果找不到就使用默认的路径 目前用于加载spring的配置文件.
	 * 
	 * <p>
	 * 
	 * 使用样例<br>
	 * String
	 * springXmlParentPath=ConfigUtil.getConfigPathFromDefault("config/applicationContext.xml","classpath:config/applicationContext.xml");<br>
	 * ApplicationContext ctx = new
	 * FileSystemXmlApplicationContext(springXmlParentPath);<br>
	 * 
	 * </p>
	 * 
	 * @param filePath
	 *            文件相对路径 优先从 工作空间下找的路径
	 * @param defaultPath
	 *            默认路径 如果从工作空间下找不到则返回该默认路径
	 * @return String 路径
	 */
	public static String getConfigPathFromDefault(String filePath, String defaultPath) {
		String workPath = System.getProperty("user.dir");
		// System.out.println("[ConfigUtil]工作空间路径: " + workPath);
		String confPath = workPath + File.separator + filePath;
		File file = new File(confPath);
		if (file.exists() && file.isFile()) {
			System.err.println("[ConfigUtil]使用工作空间路径:" + confPath);
			return confPath;
		}

		System.err.println("[ConfigUtil]使用默认路径:" + defaultPath);
		// 如果找不到就用默认的
		return defaultPath;
	}

	/**
	 * 优先从工作空间取文件 如果找不到则从类路径中取 目前用于log4j.xml文件外置化.
	 * 
	 * <p>
	 * 
	 * 使用样例<br>
	 * URL url=ConfigUtil.getUrlFromDefault("config/log4j.xml");<br>
	 * DOMConfigurator.configure(url);<br>
	 * </p>
	 * 
	 * @param filePath
	 *            文件相对路径
	 * @return URL
	 */
	public static URL getUrlFromDefault(String filePath) {
		return getUrlFromDefault(filePath, true);
	}

	/**
	 * 优先从工作空间取文件 如果找不到则从类路径中取 目前用于log4j.xml文件外置化.
	 * 
	 * <p>
	 * 
	 * 使用样例<br>
	 * URL url=ConfigUtil.getUrlFromDefault("config/log4j.xml");<br>
	 * DOMConfigurator.configure(url);<br>
	 * </p>
	 * 
	 * @param filePath
	 *            文件相对路径
	 * 
	 * @param isOutputLog
	 *            是否输出日志
	 * @return URL
	 */
	public static URL getUrlFromDefault(String filePath, boolean isOutputLog) {
		URL url = null;
		boolean isFound = false;
		String workPath = System.getProperty("user.dir");
		// System.out.println("[ConfigUtil]工作空间路径: " + workPath);
		String confPath = workPath + File.separator + filePath;
		File file = new File(confPath);
		if (file.exists() && file.isFile()) {
			if (isOutputLog) {
				System.err.println("[ConfigUtil]使用工作空间路径:" + confPath);
			}
			isFound = true;
			try {
				url = file.toURI().toURL();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		if (!isFound) {
			url = ClassLoader.getSystemResource(filePath);
			if (isOutputLog) {
				System.err.println("[ConfigUtil]使用类路径:" + url.toString());
			}
		}
		return url;
	}

}
