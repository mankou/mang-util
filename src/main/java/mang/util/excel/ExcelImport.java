package mang.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * excel导入导出工具类的辅助类 用于设置导入时的一些参数
 * */
public class ExcelImport {
	/**
	 * excel输入流
	 * */
	private InputStream inputStream;
	
	/**
	 * excel文件名
	 * */
	private String fileName;
	
	
	/**
	 * 要导入的excel的sheet.
	 * 因为一般excel都有3个sheet 这里默认导入第0个sheet.
	 * */
	private int sheetNum=0;
	
	/**
	 * 默认跳过第一行
	 * */
	private boolean isSkipFirstRow=true;;
	
	/**
	 * 要转换成bean的class.
	 * */
	private Class beanClass;
	
	
	/**
	 * 需要强制处理成 字符串类型的列
	 * 有些列如身份证、电话号码 虽然excel中有可能是数字型，但实际我们需要的是字符型 所以这里专门处理下
	 * */
	private Set<String> forceStringFieldName=new HashSet<String>();
	
	public ExcelImport(){
		
	}
	
	public ExcelImport(InputStream inputStream,String fileName,Class beanClass){
		this.inputStream=inputStream;
		this.fileName=fileName;
		this.beanClass=beanClass;
	}
	
	
	public ExcelImport(File file,Class beanClass){
		InputStream in;
		try {
			in = new FileInputStream(file);
			this.inputStream=in;
			this.fileName=file.getName();
			this.beanClass=beanClass;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ExcelImport(String filePath,Class beanClass){
		InputStream in;
		try {
			File file=new File(filePath);
			in = new FileInputStream(file);
			this.inputStream=in;
			this.fileName=file.getName();
			this.beanClass=beanClass;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addForceStringFileld(String fieldName){
		this.forceStringFieldName.add(fieldName);
	}
	
	
	/**
	 * 导入excel
	 * @return List
	 * */
	public List doImport(){
		List list=null;
		try {
//			list = ExcelUtils.importExcel(this.inputStream, this.fileName, this.sheetNum, this.isSkipFirstRow, this.beanClass);
			list = ExcelUtils.importExcel(this);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析excel 异常");
		}
		return list;
	}
	

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSheetNum() {
		return sheetNum;
	}

	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	public boolean isSkipFirstRow() {
		return isSkipFirstRow;
	}

	public void setSkipFirstRow(boolean isSkipFirstRow) {
		this.isSkipFirstRow = isSkipFirstRow;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public Set<String> getForceStringFieldName() {
		return forceStringFieldName;
	}

	public void setForceStringFieldName(Set<String> forceStringFieldName) {
		this.forceStringFieldName = forceStringFieldName;
	}

}
