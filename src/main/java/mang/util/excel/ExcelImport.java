package mang.util.excel;

import java.io.InputStream;
import java.util.List;


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
	
	
	public ExcelImport(){
		
	}
	
	public ExcelImport(InputStream inputStream,String fileName,Class beanClass){
		this.inputStream=inputStream;
		this.fileName=fileName;
		this.beanClass=beanClass;
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
	
	
	
	/**
	 * 导入excel
	 * */
	public List doImport(){
		List list=null;
		try {
			list = ExcelUtils.importExcel(this.inputStream, this.fileName, this.sheetNum, this.isSkipFirstRow, this.beanClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
