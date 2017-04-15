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

/**
 * 读取property配置文件的工具类.
 * @author man003@163.com
 * @version
 * create:2016-09-8 10:06:37
 * modify:2016-09-8 10:06:45
 *
 * */
public class PropertyUtil {
	
	/**
	 * 从工程根路径找配置文件 注意path必须以/开头 表示从工程根路径找配置文件.
	 * <p>使用方法:从工程根路径开始寻找配置文件
	 * <br>Properties ps2 = PropertyUtil.getProperties("/demo_resources2.properties");
	 * <br>System.out.println(ps.getProperty("test"));
	 * </p>
	 * @param path 配置文件路径
	 * @return Properties
	 * */
	public static Properties getProperties(String path){
		return getProperties(PropertyUtil.class,path);
	}
	
	/**
	 * 根据类所在路径和文件路径找配置文件.
	 * 
	 * <p>使用方法:如下是在PropertyTest类的方法中取得配置文件的代码片断 其从PropertyTest.class所在路径找配置文件demo3.properties
	 * <strong><br>注path不能加绝对路径/ 否则其自动从工程根路径找配置文件而不是从指定的类路径找</strong>
	 * <br>Properties ps = PropertyUtil.getProperties(PropertyTest.class,"demo3.properties");
	 * <br>System.out.println(ps.getProperty("test"));
	 * </p>
	 * @param mclass 基准类
	 * @param path 配置文件路径
	 * @return Properties
	 * */
	public static Properties getProperties(Class mclass,String path){
		Properties property = null;
		try {
			//通过getResourceAsStream 取配置文件即使打成jar包也不会报错
			property = new Properties();
			InputStreamReader sr = new InputStreamReader(mclass.getResourceAsStream(path),"utf-8"); 
			property.load(sr);
		}catch(NullPointerException e){
			//如果报错了则尝试从文件中取配置 因为一般我们会把程序打成jar包 则希望从jar包外取配置文件 所以我一般会传入一个文件路径
			File file=new File(path);
			try {
				FileInputStream fi= new FileInputStream(file);
				property.load(fi);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}catch (IOException e){
			e.printStackTrace();
			return null;
		}
		return property;
	}
	
	
	/**
	 * 优先从工作目录下开始找配置文件 如果找不到再从类路径下开始找.
	 * 到底有什么用：其既能从当前工作目录下取配置文件 也能从jar包里的类路径下取配置文件 这样jar包里可以提供默认的配置 但也可以用当前工作目录下的配置文件覆盖jar包里的配置文件<br/>
	 * 场景1-平时开发：平时开发时因当前工作目录下没有该配置文件其会从类路径下找配置文件 所以在maven工程中只要把配置文件放在resources下 其会自动打到类路径下<br/>
	 * 场景2-打成jar包后 先从当前工作目录下取配置文件 如果没有则从jar包里的类路径下取文件 这样jar包里可以提供一份默认的配置<br/>
	 * 注 什么是工作目录：平时开发时就是工作空间的目录 打成jar包后就是你执行命令时的当前目录
	 * 
	 * @param path 配置文件路径 注最好前面加上/表示从根路径下找<br/>
	 * 如/config/config.properties 表示先从 工作目录/config/config.properties找配置文件 如果没有则从 类路径/config/config.properties下找
	 * @return Properties
	 * 
	 * */
	public static Properties getPropertiesFromDefault(String path){
		String resultPath;
		//取工作目录
		String workPath = System.getProperty("user.dir");
		String confPath=workPath+"/"+path;
		File file=new File(confPath);
		if(file.exists()){
			System.out.println("从工作目录取配置文件"+confPath);
			resultPath=confPath;
		}else{
			System.out.println("从类路径取配置文件"+path);
			resultPath=path;
		}
		
		return getProperties(resultPath);
	}
	
	
	/**
	 * 利用ResourceBundle获取配置信息 并将配置信息组装成map返回
	 * @param path property路径
	 * <ul>
	 * <li>注：其是以类路径为基准 假如在你的配置文件在 src/com/mang/config.property 则你应该传入com.mang.config</li>
	 * <li>注：如上注意传入的参数不带后缀名property</li>
	 * </ul>
	 * @return Map
	 * */
	public static Map<String,String> getBundleMap(String path){
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Map<String,String> propertyMap=new HashMap<String, String>();
		// 通过资源包拿到所有的名称
        Enumeration<String> allName = bundle.getKeys();
        // 遍历
        while (allName.hasMoreElements()) {
            // 获取每一个名称
            String name = (String) allName.nextElement();
            // 利用已得到的名称通过资源包获得相应的值
            String value = bundle.getString(name);
            
            //中文乱码处理 有时property不是UTF8编码 会出现中文乱码我这里处理下
            try {
				String value_cn=new String(value.getBytes("ISO8859-1"),"GB2312");
				propertyMap.put(name, value_cn);
			} catch (UnsupportedEncodingException e) {
				propertyMap.put(name, value);
				e.printStackTrace();
			}
        }
        return propertyMap;
	}
}
