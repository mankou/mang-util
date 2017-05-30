package mang.util.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigUtil {
	
	public static List<String> defaultConfigPath=new ArrayList<String>();
	static {
		defaultConfigPath.add("config");
		defaultConfigPath.add("conf");
	}
	
	
	/**
	 * 优先从工作空间取 如果取不到就从classspath中取
	 * @param defaultPath 默认路径 如果找不到工作空间的路径 就返回该默认路径
	 * @return String 路径
	 * */
	public static String getConfigPathFromDefault(String defaultPath){
		String workPath = System.getProperty("user.dir");
		
		for(String path:defaultConfigPath){
			String confPath=workPath+File.separator+path;
			File file = new File(confPath);
			if(file.exists()&&file.isDirectory()){
				System.out.println("[ConfigUtil]使用工作空间路径:"+confPath);
				return confPath;
			}
		}
		
		System.out.println("[ConfigUtil]使用默认路径:"+defaultPath);
		//如果找不到就用默认的
		return defaultPath;
	}
	
	
	public static void addConfigPath(String path){
		defaultConfigPath.add(path);
	}
	
	public static void clearConfigPath(){
		defaultConfigPath=new ArrayList<String>();
	}

	
}
