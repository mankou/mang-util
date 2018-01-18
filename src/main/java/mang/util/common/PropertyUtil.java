package mang.util.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取property配置文件的工具类.
 * 
 * @author man003@163.com
 * @version create:2016-09-8 10:06:37 modify:2017-04-15 10:06:45
 *
 */
public class PropertyUtil {
	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);

	/*
	 * 说明基准类路径有什么用? 在一般的java工程中没用,但在web工程中有用,因为在web工程中目前我还不知道如何获取web工程所在的根路径
	 * 
	 * */
	
	/**
	 * 以基准类路径所在路径为基准 取配置
	 * 
	 * @param bclazz 基准类
	 * @param path 配置文件路径
	 * @return Properties
	 * */
	public static Properties getPropertiesFromClass(Class bclazz, String path) {
		Properties property = null;
		try {
			if(bclazz==null){
				bclazz=PropertyUtil.class;
			}
			
			// 通过getResourceAsStream 取配置文件即使打成jar包也不会报错
			property = new Properties();
			InputStreamReader sr = new InputStreamReader(bclazz.getResourceAsStream(path), "utf-8");
			property.load(sr);
		} catch (Exception e) {
			log.error("get properties error",e);
		} 
		return property;
	}
	
	
	/**
	 * 从基准类所在路径开始找properties配置并取出配置项
	 * 
	 * @param mclass
	 *            基准路径
	 * @param path
	 *            路径
	 * @param propertyName
	 *            键值
	 * @return String
	 */
	public static String getPropertyFromClass(Class mclass, String path, String propertyName) {
		Properties property = getProperties(mclass, path);
		String result = property.getProperty(propertyName);
		return result;
	}
	
	
	/**
	 * 从工程根路径找配置文件 注意path必须以/开头 表示从工程根路径找配置文件
	 * @param path
	 *            配置文件路径
	 * @return Properties
	 */
	public static Properties getPropertiesFromClass(String path) {
		if(!path.startsWith("/")){
			path="/"+path;
		}
		return getPropertiesFromClass(PropertyUtil.class, path);
	}
	
	

	/**
	 * 根据基准类所在路径和配置文件路径取配置文件,如果基准类路径下的配置文件不存在则再从文件路径中找
	 * 
	 * 注:如果在类路径下没有该配置文件,则从文件路径中找 也即优先使用类路径下的的配置文件,如果类路径下不存在该配置文件则从文件路径中找
	 * 
	 * @param bclazz
	 *            基准类
	 * @param path
	 *            配置文件路径
	 * @return Properties
	 */
	public static Properties getProperties(Class bclazz, String path) {
		/*
		 * 注 该方法实际上是一个中间产物 现在已经很少用了
		 * 刚开始的需求只是从类路径中取出配置文件即可 后来打成jar包后发现改jar包中的配置文件不方便 所以希望从文件路径中取 但配置文件不打在jar包中
		 * 
		 * 现在很不用是因为:现在都希望在jar包中提供一个默认配置,如果想替换则再在文件路径中提供一份覆盖掉 所以这里的逻辑很少用了
		 * */
		Properties property = null;
		try {
			// 通过getResourceAsStream 取配置文件即使打成jar包也不会报错
			property = new Properties();
			InputStreamReader sr = new InputStreamReader(bclazz.getResourceAsStream(path), "utf-8");
			property.load(sr);
		} catch (NullPointerException e) {

			// 如果报错了则尝试从文件中取配置 因为一般我们会把程序打成jar包 则希望从jar包外取配置文件 所以我一般会传入一个文件路径
			// 如果path以/开头则从绝对路径找,如果path不以/开头,则是相对路径从当前工作空间往下找
			File file = new File(path);
			try {
				FileInputStream fi = new FileInputStream(file);
				property.load(fi);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return property;
	}



	/**
	 * 从文件路径中取配置文件
	 * 
	 * @param filePath
	 *            配置文件路径
	 * @return Properties 配置项
	 */
	public static Properties getPropertiesFromFile(String filePath) {
		Properties property = new Properties();
		File file = new File(filePath);
		try {
			FileInputStream fi = new FileInputStream(file);
			property.load(fi);
		} catch (Exception e) {
			log.error("get properties error", e);
		}
		return property;
	}
	
	/**
	 * 从用户工作目录取properties文件
	 * @param configPath 配置文件路径
	 * @return Properties
	 */
	public static Properties getPropertiesFromUserDir(String configPath) {
		// 取工作目录
		String workPath = System.getProperty("user.dir");
		String confPath = workPath + File.separator + configPath;
		log.debug("userDirPath:{}", confPath);
		File file = new File(confPath);
		if (file.exists()) {
			log.info("get properties from user.dir {}",confPath);
			return getPropertiesFromFile(confPath);
		}
		return null;
	}

	/**
	 * 优先从工作目录下找配置文件如果找不到再从类路径下开始找.
	 * 
	 * <p>
	 * 到底有什么用：其既能从当前工作目录下取配置文件 也能从jar包里的类路径下取配置文件 这样jar包里可以提供默认的配置
	 * 但也可以用当前工作目录下的配置文件覆盖jar包里的配置文件<br>
	 * 
	 * 场景1-平时开发：平时开发时因当前工作目录下没有该配置文件其会从类路径下找配置文件 所以在maven工程中只要把配置文件放在resources下
	 * 其会自动打到类路径下<br>
	 * 
	 * 场景2-打成jar包后 先从当前工作目录下取配置文件 如果没有则从jar包里的类路径下取文件 这样jar包里可以提供一份默认的配置<br>
	 * 注 什么是工作目录：平时开发时就是工作空间的目录 打成jar包后就是你执行命令时的当前目录
	 * 
	 * @param path
	 *            配置文件路径 注最好前面加上/表示从根路径下找<br>
	 *            如/config/config.properties 表示先从
	 *            工作目录/config/config.properties找配置文件 如果没有则从
	 *            类路径/config/config.properties下找
	 * @return Properties
	 * 
	 */
	public static Properties getPropertiesFromDefault(String path) {
		Properties property=null;
		String resultPath;
		// 取工作目录
		String workPath = System.getProperty("user.dir");
		String confPath = workPath +File.separator+ path;
		File file = new File(confPath);
		if (file.exists()) {
			log.info("get properties from user.dir",confPath);
			property=getPropertiesFromFile(confPath);
		} else {
			log.info("get properties from clazz path",path);
			property=getPropertiesFromClass(path);
		}
		return property;
	}
	

	/**
	 * 优先从工作目录的配置文件中取配置项,如果工作目录中的配置文件不存在或者配置项没有配置或者配置项为空则再从基准类路径中取配置项
	 * 
	 * 这样可以在jar包中提供一份默认全量配置,但可以在工作目录下配置文件中覆盖掉部分配置项
	 * 
	 * @param userDirConfigPath
	 *            工作目录下配置文件的路径
	 * 
	 * @param baseClazz
	 *            基准类
	 * 
	 * @param baseClazzRelativeConfigPath
	 *            相对基准类路径的相对路径
	 * 
	 * @param configItem
	 *            配置项
	 */
	public static String getPriorValue(String userDirConfigPath, Class baseClazz, String baseClazzRelativeConfigPath,
			String configItem) {
		Properties userDirProperties = getPropertiesFromUserDir(userDirConfigPath);
		if (userDirProperties == null) {
			userDirProperties = new Properties();
		}
		
		Properties clazzProperties = getPropertiesFromClass(baseClazz, baseClazzRelativeConfigPath);
		if (clazzProperties == null) {
			clazzProperties = new Properties();
		}

		String userDirValue = userDirProperties.getProperty(configItem);
		String clazzValue = clazzProperties.getProperty(configItem);
		log.debug("userDirValue:{},clazzValue:{}",new Object[]{userDirValue,clazzValue});
		
		if(userDirValue!=null && !"".equals(userDirValue)){
			log.debug("prior select userDirValue {}",userDirValue);
			return userDirValue;
		}else{
			log.debug("prior select clazzValue {}",clazzValue);
			return clazzValue;
		}
	}

	/**
	 * 优先从工作目录的配置文件中取配置项,如果工作目录中的配置文件不存在或者配置项为空则再从基准类路径中取配置项
	 * 
	 * @param configPath
	 *            配置文件路径
	 * 
	 * @param baseClazz
	 *            基准类
	 * 
	 * @param configItem
	 *            配置项
	 * 
	 * @return String 配置值
	 */
	public static String getPriorValue(String configPath, Class baseClazz, String configItem) {
		String result = getPriorValue(configPath, baseClazz, configPath, configItem);
		return result;
	}
	
	/**
	 * 优先从工作目录下的配置文件取配置项,如果工作目录下的配置文件不存在或者配置项为空则从工程根路径取配置项
	 * 
	 * 使用背景:有时连基准类也不想指定,因为配置文件的路径有可能如下 config/setting.properties,其不需要指定基准类
	 * */
	public static String getPriorValue(String configPath,String configItem) {
		if(!configPath.startsWith("/")){
			configPath="/"+configPath;
		}
		String result = getPriorValue(configPath, null, configPath, configItem);
		return result;
	}

	
	/**
	 * 利用ResourceBundle获取配置信息 并将配置信息组装成map返回
	 * 
	 * @param path
	 *            property路径
	 *            <ul>
	 *            <li>注：其是以类路径为基准 假如在你的配置文件在 src/com/mang/config.property
	 *            则你应该传入com.mang.config</li>
	 *            <li>注：如上注意传入的参数不带后缀名property</li>
	 *            </ul>
	 * @return Map
	 */
	public static Map<String, String> getBundleMap(String path) {
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Map<String, String> propertyMap = new HashMap<String, String>();
		// 通过资源包拿到所有的名称
		Enumeration<String> allName = bundle.getKeys();
		// 遍历
		while (allName.hasMoreElements()) {
			// 获取每一个名称
			String name = (String) allName.nextElement();
			// 利用已得到的名称通过资源包获得相应的值
			String value = bundle.getString(name);

			// 中文乱码处理 有时property不是UTF8编码 会出现中文乱码我这里处理下
			try {
				String value_cn = new String(value.getBytes("ISO8859-1"), "GB2312");
				propertyMap.put(name, value_cn);
			} catch (UnsupportedEncodingException e) {
				propertyMap.put(name, value);
				e.printStackTrace();
			}
		}
		return propertyMap;
	}
}
