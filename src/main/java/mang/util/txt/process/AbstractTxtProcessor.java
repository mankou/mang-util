package mang.util.txt.process;

import java.io.File;
import mang.util.txt.reader.SimpleTxtReader;
import mang.util.txt.reader.TxtReader;
import mang.util.txt.writer.SimpleTxtEncodeWriter;
import mang.util.txt.writer.TxtWriter;

public  class AbstractTxtProcessor implements TxtFileProcessor{
	
	/**
	 * 读取文件时编码
	 * */
	private String readCharset="UTF-8";
	
	/**
	 * 写文件时的编码
	 * */
	private String writeCharset="UTF-8";
	
	
	/**
	 * lineProcess
	 * */
	private TxtLineProcessor lineHandleProcessor;
	
	/**
	 * lineFilter
	 * */
	private TxtLineFilterProcessor lineFilterProcessor;


	public void processSingleFile(String sourceFilePath, String targetFilePath) {
		File file = new File(sourceFilePath);
		File targetFile=new File(targetFilePath);
		TxtReader reader = new SimpleTxtReader(file,readCharset);
		TxtWriter txtWriter = new SimpleTxtEncodeWriter(targetFile, writeCharset);
		while (reader.hasNext()) {
			String currentLine = reader.readLine();
			
			//true 就是要,false就是不要
			if(!lineFilterProcessor.beforeFilter(currentLine)){
				continue;
			}
			
			String processLine=null;
			processLine=lineHandleProcessor.processLine(currentLine);
			
			if(!lineFilterProcessor.afterFilter(processLine)){
				continue;
			}
			txtWriter.writeLine(processLine);
		}
		reader.close();
		txtWriter.close();
		
		after();
	}
	

	public void after(){
		
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


	public TxtLineProcessor getLineHandleProcessor() {
		return lineHandleProcessor;
	}


	public void setLineHandleProcessor(TxtLineProcessor lineHandleProcessor) {
		this.lineHandleProcessor = lineHandleProcessor;
	}


	public TxtLineFilterProcessor getLineFilterProcessor() {
		return lineFilterProcessor;
	}


	public void setLineFilterProcessor(TxtLineFilterProcessor lineFilterProcessor) {
		this.lineFilterProcessor = lineFilterProcessor;
	}

}
