package mang.util.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * zip压缩文件工具类.
 * 可压缩文件 也可压缩文件夹
 * 
 * @author man003@163.com
 * @version
 * create:2016-09-28 17:45:08
 * modify:2016-09-28 17:45:11
 * */
public class ZipUtil {
	private static Logger logger = Logger.getLogger(ZipUtil.class);
	private static final int BUFFER = 8192;
  
  /**
   * zip压缩文件或文件（只能是一个文件或文件夹）
   * 
   * @param sourcePath 要压缩的文件的路径 可以是文件夹的路径 也可以是文件的路径
   * @param zipFilePath 压缩文件路径
   * @param baseDir 压缩时的基准路径
   * <ol>
   * <li> 如果想将文件压缩到根路径 则传入 "" 即可 </li>
   * <li> 如果希望压缩文件的根路径为 123 则传入"123" 则压缩文件中的根路径是"123/你要压缩的文件" </li>
   * <li> 如果希望压缩文件的根路径为 123/456 则传入"123/456" 则压缩文件中的根路径是"123/456/你要压缩的文件" </li>
   * <ol>
   * */
	public static void compress(String sourcePath, String zipFilePath, String baseDir) {
		
		List sourceListPath = new ArrayList<String>();
		sourceListPath.add(sourcePath);
		compress(sourceListPath, zipFilePath, baseDir);
	}
	
	 /**
	   * zip压缩一组文件或文件
	   * 
	   * @param sourcePathList 要压缩的文件的路径 是一个list<String>
	   * @param zipFilePath 压缩文件路径
	   * @param baseDir 压缩时的基准路径
	   * <ol>
	   * <li> 如果想将文件压缩到根路径 则传入 "" 即可 </li>
	   * <li> 如果希望压缩文件的根路径为 123 则传入"123" 则压缩文件中的根路径是"123/你要压缩的文件" </li>
	   * <li> 如果希望压缩文件的根路径为 123/456 则传入"123/456" 则压缩文件中的根路径是"123/456/你要压缩的文件" </li>
	   * <ol>
	   * */
	public static void compress(List<String> sourcePathList, String zipFilePath, String baseDir) {

		try {

			FileUtil.mkParentDir(new File(zipFilePath));

			FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			out.setEncoding(System.getProperty("sun.jnu.encoding"));// 设置文件名编码方式

			for (String source : sourcePathList) {
				if (baseDir != null && !"".equals(baseDir)) {
					baseDir = FileUtil.processEndSeparator(baseDir);
				}
				compress(new File(source), out, baseDir);
			}
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

  private static void compress(File file, ZipOutputStream out, String basedir){
    /* 判断是目录还是文件 */
    if (file.isDirectory())
    {
      compressDirectory(file, out, basedir);
    }
    else
    {
      compressFile(file, out, basedir);
    }
  }

  /** 压缩一个目录 */
  private static void compressDirectory(File dir, ZipOutputStream out, String basedir)
  {
    if (!dir.exists()) return;
    File[] files = dir.listFiles();
    for (int i = 0; i < files.length; i++)
    {
      /* 递归 */
      compress(files[i], out, basedir + dir.getName() + "/");
    }
  }

  /** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			logger.warn("[zipUtil]" + "文件不存在:" + file.getAbsolutePath());
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
  @SuppressWarnings("unchecked")
	public static void zip(Map<String, List<String>> zipInfoMap, String zipFileName) {
		try {
			File zipFile = new File(zipFileName);
			// if(srcPathNameList != null && srcPathNameList.size() > 0){
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			out.setEncoding(System.getProperty("sun.jnu.encoding"));// 设置文件名编码方式

			Set set = zipInfoMap.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map.Entry<String, List<String>> entry1 = (Map.Entry<String, List<String>>) i.next();
				String basedir = entry1.getKey() + "/";
				List<String> srcPathNameList = entry1.getValue();
				for (String srcPathName : srcPathNameList) {
					File file = new File(srcPathName);
					if (file.exists())
						compress(file, out, basedir);
				}
			}
			out.close();
			// }
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
  /**
	 * 解压到指定目录
	 * @param zipPath
	 * @param descDir
	 * @author isea533
	 */
	public static void unZipFiles(String zipPath,String descDir)throws IOException{
		unZipFiles(new File(zipPath), descDir);
	}
	/**
	 * 解压文件到指定目录
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	@SuppressWarnings("unchecked")
	public static void unZipFiles(File zipFile,String descDir)throws IOException{
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile,"GB18030");//解决中文乱码问题
		for(Enumeration entries = zip.getEntries();entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry)entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
			//判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if(!file.exists()){
				file.mkdirs();
			}
			//判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if(new File(outPath).isDirectory()){
				continue;
			}
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while((len=in.read(buf1))>0){
				out.write(buf1,0,len);
			}
			in.close();
			out.close();			
		}
	}

}
