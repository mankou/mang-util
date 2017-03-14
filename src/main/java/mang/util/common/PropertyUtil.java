package mang.util.common;

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
	 * 
	 * <p>使用方法:从工程根路径开始寻找配置文件
	 * <font color="red">
	 * <br>Properties ps2 = PropertyUtil.getProperties("/demo_resources2.properties");
	 * <br>System.out.println(ps.getProperty("test"));
	 * </font>
	 * </p>
	 * */
	public static Properties getProperties(String path){
		return getProperties(PropertyUtil.class,path);
	}
	
	/**
	 * 根据类所在路径和文件路径找配置文件.
	 * 
	 * <p>使用方法:如下是在PropertyTest类的方法中取得配置文件的代码片断 其从PropertyTest.class所在路径找配置文件demo3.properties
	 * <strong><br>注path不能加绝对路径/ 否则其自动从工程根路径找配置文件而不是从指定的类路径找</strong>
	 * <font color="red">
	 * <br>Properties ps = PropertyUtil.getProperties(PropertyTest.class,"demo3.properties");
	 * <br>System.out.println(ps.getProperty("test"));
	 * </font>
	 * </p>
	 * */
	public static Properties getProperties(Class mclass,String path){
		Properties property = null;
		try {
			property = new Properties();
			InputStreamReader sr = new InputStreamReader(mclass.getResourceAsStream(path),"utf-8"); 
			property.load(sr);
		}catch (IOException e){
			e.printStackTrace();
			return null;
		}
		return property;
	}
	
	/**
	 * 利用ResourceBundle获取配置信息 并将配置信息组装成map返回
	 * @param path property路径
	 * <ul>
	 * <li>注：其是以类路径为基准 假如在你的配置文件在 src/com/mang/config.property 则你应该传入com.mang.config</li>
	 * <li>注：如上注意传入的参数不带后缀名property</li/
	 * </ul>
	 * 
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
