package mang.util.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.log4j.Logger;

/**
 * 文件工具类.
 * 
 * @author man003@163.com
 * @version 
 * create:2016-10-10 10:24:29
 * modfiy:2016-10-10 10:24:33
 * 
 * */
public class FileUtil {
	private static Logger logger = Logger.getLogger(FileUtil.class);
	/** 
	 * 根据传入的字符串创建文件.
	 * <p>如果文件不存在会自动创建,如果文件存在会覆盖
	 * @param filePath 文件路径
	 * @param txt 文件内容
	 * */
	public static void createFile(String filePath,String txt){
		  try {  
	            File f = new File(filePath);  
	            String parentPath = f.getParent();
	            FileUtil.mkDir(new File(parentPath));
	            if (f.exists()) {  
	                logger.warn("[FileUtil]"+"文件存在"+f.getAbsolutePath());
	            } else {  
	                f.createNewFile();// 不存在则创建  
	            }  
	  
	            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
	            output.write(txt);  
	            output.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	  
	        }  
		
	}
	
	/**
	 * 判断目录是否存在 如果不存在则创建.
	 * <p>这里是递归创建，即如果父目录不存在会先新建父目录
	 * @param fold 必须是fold 而不能是file
	 * */
	public static void mkDir(File fold) {
		if(fold.getParentFile()==null){
			return;
		}
		if (fold.getParentFile().exists()) {
			fold.mkdir();
		} else {
			mkDir(fold.getParentFile());
			fold.mkdir();
		}
	}
	
	/**
	 * 判断某一文件或者目录的父目录是否存在 如果不存在则创建父目录.
	 * <p>多用于rename时 因rename时必须保存目标文件的父目录存在
	 * */
	public static void mkParentDir(File file){
		File parent=file.getParentFile();
		if(parent==null){
			return;
		}else{
			FileUtil.mkDir(parent);
		}
	}
	
	/**
	 * 删除某目录下的所有文件.
	 * 删除某一路径下所有文件 但是该目录不删除 即清空该目录
	 * */
	 public static boolean clearFolder(String foldPath) {
	       boolean flag = false;
	       File file = new File(foldPath);
	       if (!file.exists()) {
	         return flag;
	       }
	       if (!file.isDirectory()) {
	         return flag;
	       }
	       String[] tempList = file.list();
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (foldPath.endsWith(File.separator)) {
	             temp = new File(foldPath + tempList[i]);
	          } else {
	              temp = new File(foldPath + File.separator + tempList[i]);
	          }
	          
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				clearFolder(foldPath+"/"+tempList[i]); //递归调用
				temp.delete();
				flag = true;
			}
	       }
	       return flag;
	     }
	 
	 
	 
	/**
	 * 删除文件.
	 * <p>文件、文件夹都能删除
	 * @param path 文件或者目录路径
	 * <li>目录路径示例 c:/Users/mang/Desktop/haiguan/Send/test/ 或者 c:/Users/mang/Desktop/haiguan/Send/test 都行
	 * <li>文件路径示例 c:/Users/mang/Desktop/haiguan/Send/test/test.txt 
	 * */
	public static void delFile(String path) {
		try {
			File file = new File(path);
			
			if(file.isDirectory()){
				clearFolder(path); // 清空目录
			}
			
			file.delete(); //如果是文件或者空文件夹该语句都能删除 所以上面需要判断如果是目录需要先清空
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 将一个文件或者一个目录 移到另一个目录下.
	 * @param sourcePath 源文件路径 可以是文件也可以是目录 如c:/Users/mang/Desktop/haiguan/Send/
	 * @param destPath 目标目录 必须是一个目录 
	 * @param isOverWrite 是否覆盖 如果移动时有同名是否覆盖
	 * */
	public static void moveFileOrFoldToAnotherFold(String sourcePath,String destPath,boolean isOverWrite){
		if(!sourcePath.equals(File.separator)){
			sourcePath+=File.separator;
		}
		
		if(!destPath.equals(File.separator)){
			destPath+=File.separator;
		}
	
		//rename 前必须确保目标路径的父目录存在 所以这里如果目标路径不存 先建立个
		File destFold=new File(destPath);
		FileUtil.mkDir(destFold);
		
		File sourceFile = new File(sourcePath);
		File destFile = new File(destPath+sourceFile.getName());
		
		//如果已经存在 并且需要覆盖时则先把原文件删除掉
		if(destFile.exists()&&isOverWrite){
			System.out.println("目标文件"+destFile+"已存在,因配置了覆盖,所以先删除");
			FileUtil.delFile(destPath+sourceFile.getName());
		}
		
		sourceFile.renameTo(destFile);
	}
	
	/**
	 * 将一个目录下的文件移动到另一个目录下.
	 * <p>是将该目录下的文件移到到另一个目录 不是该目录<br>
	 * 如a 目录下有 a1,a2,移动到b目录 则b目录下有a1,a2
	 * @param sourceFoldPath 源目录
	 * @param destFoldPath 目标目录
	 * @param isCleanDestFold 是否清空目录目录  即如果目录下已经有文件是否清空该目录
	 * <li>如果目标目录下已经有文件 可根据isCleanDestFold 决定是否清空目录目录</li>
	 * <li>如果目标目录下有文件 也不配置了不清空目标目录,则仍然可以将源目录中不同名文件移动到目标路径下（如果同名按照isOverWrite的配置进行处理）(如海关出入卡口xml本地生成路径与移除路径一致时使用过)</li>
	 * @param isOverWrite 是否覆盖 如果移动时有同名文件是否覆盖 true表示覆盖 false表示不覆盖
	 * <li>如果移动时出现同名文件 则根据isOverWrite进行处理处理</li>
	 * <li>如果移动时出现了同名文件 则肯定是目标目录存在并且配置了isCleanDestFold为false</li>
	 * */
	public static void moveFoldfilesToAnotherFold(String sourceFoldPath,String destFoldPath,boolean isCleanDestFold,boolean isOverWrite){
		File sourceFold = new File(sourceFoldPath);
		//如果源目录都不存在 则直接返回
		if(!sourceFold.exists()){
			return;
		}
		
		//因rename时需保证目标对象的父目录存在  所以这里先处理下
		File destFold = new File(destFoldPath);
		FileUtil.mkParentDir(destFold);
		
		if(!destFold.exists()){
			//如果目标目录不存在直接rename 再新建一个空目录
			sourceFold.renameTo(destFold);
			FileUtil.mkDir(sourceFold);
		}else{
			//如果目标目录存在
			if(isCleanDestFold){
				FileUtil.delFile(destFoldPath);
				sourceFold.renameTo(destFold);
				
				FileUtil.mkDir(sourceFold);
			}else{
				//如果目标目录存在 并且不允许清空目标目录 则只能遍历源目录下的文件 一个一个rename过去
				String[] tempList = sourceFold.list();
				for(int i=0;i<tempList.length;i++){
					FileUtil.moveFileOrFoldToAnotherFold(sourceFoldPath+tempList[i], destFoldPath, isOverWrite);
				}
			}
		}
	}
	
	/**
	 * 处理路径末尾分隔符.
	 * 如果末尾没有路径分隔符则加上 即如果 传来c:\Users\mang\Desktop\haiguan\Send 则处理成c:\Users\mang\Desktop\haiguan\Send\ <br>
	 * 如果是在linux下，则如果传来 /home/oracle 会处理成 /home/oracle/
	 * 注：有时在windows下你也可能把路径写成c:/Users/mang/Desktop/haiguan/Send 然后处理成c:/Users/mang/Desktop/haiguan/Send\ 但经测试不影响使用
	 * */
	public static String processEndSeparator(String path){
		//TODO 还有一种情况 就是有可能是windows下 但其传的路径类似  "123/" 则你会处理成 "123/\" 也不对
		
		 if (!(path.endsWith(File.separator) || path.endsWith("\\") || path.endsWith("/"))) {
			 path+=File.separator;
		 }
		 return path;
	}
	
	
	/**
	 * 根据文件路径获取文件名.
	 * 
	 * */
	public static String getFileName(String path){
		File file=new File(path);
		String fileName=file.getName();
		return fileName;
	}
	
	/**
	 * 根据文件名获取文件后缀名.
	 * 如文件名是 test1.xlsx 则返回xlsx
	 * */
	public static String getFileType(String fileName){
	    return fileName.substring(fileName.lastIndexOf(".")+1);
	}

}
