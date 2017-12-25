package mang.util.txt.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mang.util.txt.linefilter.LineFilter;
import mang.util.txt.linehandle.LineHandler;
import mang.util.txt.reader.SimpleTxtReader;
import mang.util.txt.reader.TxtReader;
import mang.util.txt.writer.SimpleTxtEncodeWriter;
import mang.util.txt.writer.TxtWriter;

public abstract class AbstractTxtFilterProcess implements TxtFilterProcess {
	
	/**
	 * 读取文件时编码
	 * */
	private String readCharset="UTF-8";
	
	/**
	 * 写文件时的编码
	 * */
	private String writeCharset="UTF-8";
	
	
	private List<LineFilter> beforeLineFilter=new ArrayList<LineFilter>();
	
	
	private List<LineFilter> afterLineFilter=new ArrayList<LineFilter>();
	
	
	private List<LineHandler> handleList=new ArrayList<LineHandler>();
	

	@Override
	public abstract boolean beforeFilter(String line,List<LineFilter> filterList);
	
	@Override
	public abstract String processLine(String line, List<LineHandler> handleList);
	
	@Override
	public abstract boolean afterFilter(String line,List<LineFilter> filterList);


	public void processSingleFile(String sourceFilePath, String targetFilePath) {
		File file = new File(sourceFilePath);
		File targetFile=new File(targetFilePath);
		TxtReader reader = new SimpleTxtReader(file,readCharset);
		TxtWriter txtWriter = new SimpleTxtEncodeWriter(targetFile, writeCharset);
		while (reader.hasNext()) {
			String currentLine = reader.readLine();
			
			//true 就是要,false就是不要
			if(!beforeFilter(currentLine,beforeLineFilter)){
				continue;
			}
			String processLine=null;
			processLine=processLine(currentLine,handleList);
			if(!afterFilter(processLine,afterLineFilter)){
				continue;
			}
			txtWriter.writeLine(processLine);
		}
		reader.close();
		txtWriter.close();

	}
	
	public AbstractTxtFilterProcess addBeforeFilter(LineFilter lineFilter){
		this.beforeLineFilter.add(lineFilter);
		return this;
	}
	
	public AbstractTxtFilterProcess addAfterFilter(LineFilter lineFilter){
		this.afterLineFilter.add(lineFilter);
		return this;
	}
	
	
	public AbstractTxtFilterProcess addHandle(LineHandler lineHandler){
		this.handleList.add(lineHandler);
		return this;
	}
	
	
	public String getReadCharset() {
		return readCharset;
	}

	public void setReadCharset(String readCharset) {
		this.readCharset = readCharset;
	}

	public String getWriteCharset() {
		return writeCharset;
	}

	public void setWriteCharset(String writeCharset) {
		this.writeCharset = writeCharset;
	}


	public List<LineFilter> getBeforeLineFilter() {
		return beforeLineFilter;
	}


	public void setBeforeLineFilter(List<LineFilter> beforeLineFilter) {
		this.beforeLineFilter = beforeLineFilter;
	}


	public List<LineFilter> getAfterLineFilter() {
		return afterLineFilter;
	}


	public void setAfterLineFilter(List<LineFilter> afterLineFilter) {
		this.afterLineFilter = afterLineFilter;
	}


	public List<LineHandler> getHandleList() {
		return handleList;
	}


	public void setHandleList(List<LineHandler> handleList) {
		this.handleList = handleList;
	}

}
