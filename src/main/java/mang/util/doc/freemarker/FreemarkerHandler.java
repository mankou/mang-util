package mang.util.doc.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import mang.util.common.FileUtil;

/**
 * 用freemarker的方式生成word的处理类.
 * 程序参照自：关于Freemarker生成word的使用(java生成word)
 * 
 * 使用说明
 * <ol>
 * <li>word中不能有图片 否则你都找不到生成的xml模板中的变量</li>
 * <li>经测试此种方法只能处理 xml格式是 2003版的那种，所以word模板另存为xml时选择2003版的那种 否则生成的word打开时报"无法打开，因为内容有问题"的错误，但关闭提示后word也能打开</li>
 * <li>最佳实践：生成的xml模板可放在eclipse中格式化下 方便你查找变量</li>
 * <li>最佳实践：变量不要用title content等xml模板中常出现的单词 免得你不好找 用汉字最好找</li>
 * </ol>
 * 
 * @author man003@163.com
 * @version 
 * create 2014-08-10 
 * modify 2016-09-30
 * **/
public class FreemarkerHandler {
	private Configuration configuration = null;
	private Map<String,Object> dataMap= new HashMap<String,Object>();
	
	private String rootPath; //工程根路径
	private String defaultExportPath="downloadWord/";//默认路径 如果转入的路径为空 则该路径生效
	private String outputPath; //word导出路径 注其是绝对路径
	private String xmlName; //xml文件名称  如wordDemo.xml
	private String xmlPath; //xml文件路径 注其是绝对路径 如
	private String xmlParentPath; //xml文件所以父路径。
	
	/**
	 * 如果输出文件名重复是否处理 默认不处理
	 * false 表示不处理 则直接覆盖
	 * true 表示处理 目前有2种处理策略  默认是"date策略" 
	 * 策略1："date"策略 在文件名后面加时间后缀如文件名为 output.doc 则处理成output1609301050.doc这样的格式
	 * 策略2："num" 策略 是在文件名后面加数字的策略 如文件名为output.doc 如果文件名已经存在则处理成output1.doc 如果还存在则继续 只到找到不存在的文件名
	 * */
	private boolean isProcessDuplicate=false;
	
	/**
	 * 文件名重复时 的处理策略 "date" 或者"num"
	 * */
	private String duplicateProcessStrategy="date";
	
	/**
	 * 如果采用"date"处理策略时的 默认时间格式
	 * */
	private String defaultOutputFileDateFormatStr="yyMMddHHmmss";
	/**
	 * @param xmlTemplatePath xml模板的绝续路径
	 * @param ourputPath 导出word的绝对路径
	 * @param dataMap word数据
	 * */
	public FreemarkerHandler(String xmlTemplatePath,String ourputPath,Map<String,Object> dataMap){
		
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		
		this.xmlPath=xmlTemplatePath;
		this.outputPath=ourputPath;
		this.dataMap=dataMap;
		
	}
	
	
	public FreemarkerHandler(){
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}
	
	/**
	 * 生成word
	 * */
	public File createDoc() {
		try {
			
			//解析xmlpath 的分别将xml完整路径、父路径、xmlname赋值给相关变量 
			this.processXMLPath();
			
			//处理exportPath
			this.processExportPath();
			
			
			configuration.setDirectoryForTemplateLoading(new File(xmlParentPath));
			Template template = configuration.getTemplate(xmlName);	// 装载xml模板
			File outFile = new File(outputPath);
			FileUtil.mkDir(new File(outFile.getParent()));//如果其父路径不存在,则先创建
			outFile.createNewFile();
			
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
			template.process(dataMap, out);
			
			
			out.close();
			return outFile;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 用于生成导出的文件名。如果exportPath中的文件名重复 则  输出文件格式类似outFileDoc1.doc 依次类推
	 * @param exportPath
	 */
	private String getFilePath_Number(){
		File outFile = new File(outputPath);
		
		String path = outFile.getParent();
		path = path.replace('\\', '/'); //处理windows路径问题 如果输入的路径是"d:\\word\\outFileDoc.doc" 也能处理
		
		//如果路径不存在则新创建路径 否则会报错
		File fold = new File(path);
		if(!fold.exists()){
			fold.mkdir();
		}	
		
		String fileFullName = outFile.getName();
		String fileName = fileFullName.substring(0,fileFullName.lastIndexOf("."));
		String postfix = fileFullName.substring(fileFullName.lastIndexOf("."));
		
		int i=1;
		while(outFile.exists()){
			String fileName_Temp =  path+File.separator+fileName+i+postfix;
			outFile = new File(fileName_Temp);
			i++;
		}
		
		return outFile.getPath();
	}
	
	/**
	 * 处理输出路径。  如文件名为 output.doc  则可以处理成类似outFileDoc201408101935.doc
	 * @param exportPath
	 */
	private String getFilePath_Time(){
		File outFile = new File(outputPath);
		
		String path = outFile.getParent();
		path = path.replace('\\', '/'); //处理windows路径问题 如果输入的路径是"d:\\word\\outFileDoc.doc" 也能处理
		
		String fileFullName = outFile.getName();
		String fileName = fileFullName.substring(0,fileFullName.lastIndexOf("."));
		String postfix = fileFullName.substring(fileFullName.lastIndexOf("."));
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(defaultOutputFileDateFormatStr);
		String dateFormat = simpleDateFormat.format(new Date());
		return path+"/"+fileName+dateFormat+postfix;
		
	}
	
	/**
	 * 用于生成默认的导出的文件名。  输出文件格式类似201408101935.doc
	 */
	private String getDefaultFilePath(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(defaultOutputFileDateFormatStr);
		String dateFormat = simpleDateFormat.format(new Date());
		return rootPath+defaultExportPath+"/"+dateFormat+".doc";
	}
	
	/**
	 * create:201503161902
	 * modify:201503161902
	 * 解析xmlpath 的分别将xml完整路径、父路径、xmlname赋值给相关变量 
	 * */
	private void processXMLPath(){
		File file = new File(xmlPath);
		
		//解析出xml的父路径
		xmlParentPath = file.getParent();
		xmlParentPath = xmlParentPath.replace('\\', '/'); //处理windows路径问题 如果输入的路径是"d:\\word\\outFileDoc.doc" 也能处理
		
		xmlName=file.getName(); //解析出xml的文件名
		
	}
	
	/**
	 * 处理输出路径
	 * 如输出路径为空 则采用默认的输出路径
	 * 如果设置了处理输出路径并且输出路径不为空 则处理文件名为单时间缀的那种 如output150316185947.doc
	 * */
	private void processExportPath(){
		if(isProcessDuplicate){
			if("date".equals(duplicateProcessStrategy)){
				this.outputPath=getFilePath_Time();
			}else if("num".equals(duplicateProcessStrategy)){
				this.outputPath=getFilePath_Number();
			}else{
				throw new RuntimeException("请设置重复处理策略");
			}
		}
	}
	public Map<String,Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String,Object> dataMap) {
		this.dataMap = dataMap;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	
	/**
	 * 如果输出文件名重复是否处理 
	 * true表示处理 false表示不处理 如果没有设置则走默认值 false
	 * */
	public void setProcessDuplicate(boolean isProcessDuplicate) {
		this.isProcessDuplicate = isProcessDuplicate;
	}
	
	/**
	 * 设置重复处理策略 "date" 或者"num"
	 * 策略1："date"策略 在文件名后面加时间后缀如文件名为 output.doc 则处理成output1609301050.doc这样的格式
	 * 策略2："num" 策略 是在文件名后面加数字的策略 如文件名为output.doc 如果文件名已经存在则处理成output1.doc 如果还存在则继续 只到找到不存在的文件名
	 * 
	 * */
	public void setDuplicateProcessStrategy(String duplicateProcessStrategy) {
		this.duplicateProcessStrategy = duplicateProcessStrategy;
	}
	
	/**
	 * 设置如果采用"date"处理策略时的 默认时间格式
	 * */
	public void setDefaultOutputFileDateFormatStr(String defaultOutputFileDateFormatStr) {
		this.defaultOutputFileDateFormatStr = defaultOutputFileDateFormatStr;
	}
	
	
}
