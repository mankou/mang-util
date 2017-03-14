package mang.util.demo.zip;

import java.util.ArrayList;
import java.util.List;

import mang.util.common.ZipUtil;

/**
 * ZipUtil 使用示例.
 * */
public class ZipDemo {

	public static void main(String[] args) {
		
		//示例压缩多个文件（包括文件夹）
		System.out.println("示例压缩多个文件");
		doTestZipManyFile();
		
		//示例压缩文件 或文件夹
		System.out.println("示例压缩一个 文件或文件夹");
		doTestZipFile();
		

	}
	
	/**
	 * 示例：示例压缩多个文件
	 * */
	public static void doTestZipManyFile(){
		String zipFilePath="c:/Users/mang/Desktop/testZip/zip/outManyFile.zip";
		
		List<String> fileList=new ArrayList<String>();
		fileList.add("c:/Users/mang/Desktop/testZip/doc/0902线索.docx");
		fileList.add("c:/Users/mang/Desktop/testZip/doc/unieap.license");
		fileList.add("c:/Users/mang/Desktop/testZip/doc2");
		
		//baseDir 压缩文件中的根目录 这里传入123则 压缩文件中的根目录是123 如果传入"" 则没有根目录 一打开压缩文件就文件
		ZipUtil.compress(fileList, zipFilePath, "123");
	}
	

	/**
	 * 示例压缩文件或文件夹
	 * */
	public static void doTestZipFile(){
		String zipFilePath="c:/Users/mang/Desktop/testZip/zip/outFile.zip";
		String filePath="c:/Users/mang/Desktop/testZip/doc/";
		
		
		ZipUtil.compress(filePath, zipFilePath, "123");
	}

}
