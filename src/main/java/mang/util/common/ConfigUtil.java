package mang.util.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigUtil {
	
	public static List<String> defaultConfigPath=new ArrayList<String>();
	static {
		defaultConfigPath.add("config");
//		defaultConfigPath.add("target/classes/config");
		defaultConfigPath.add("conf");
//		defaultConfigPath.add("target/classes/conf");
	}
	
	
	public static String getConfigPath(){
		String workPath = System.getProperty("user.dir");
		
		for(String path:defaultConfigPath){
			String confPath=workPath+File.separator+path;
			File file = new File(confPath);
			if(file.exists()&&file.isDirectory()){
				return confPath;
			}
		}
		
		//如果都找不到则默认从 /config中找  当你不想写配置文件就用jar包中的/config配置文件时很有用
		return "/config";
	}
	
	/**
	 * 优先从工作空间取 如果取不到就从classspath中取
	 * */
	public static String getConfigPathFromDefault(String defaultPath){
		String workPath = System.getProperty("user.dir");
		
		for(String path:defaultConfigPath){
			String confPath=workPath+File.separator+path;
			File file = new File(confPath);
			if(file.exists()&&file.isDirectory()){
				System.out.println("使用工作空间路径:"+confPath);
				return confPath;
			}
		}
		//如果找不到就从类路径里取
		return "classpath:";
	}
	
	
	public static void addConfigPath(String path){
		defaultConfigPath.add(path);
	}
	
	public static void clearConfigPath(){
		defaultConfigPath=new ArrayList<String>();
	}

	
	
	
}
