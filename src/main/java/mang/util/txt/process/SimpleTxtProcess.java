package mang.util.txt.process;

import java.io.File;
import java.util.List;

import mang.util.txt.linehandle.LineHandler;
import mang.util.txt.reader.SimpleTxtReader;
import mang.util.txt.reader.TxtReader;
import mang.util.txt.writer.SimpleTxtEncodeWriter;
import mang.util.txt.writer.TxtWriter;

public class SimpleTxtProcess implements TxtProcess {
	
	/**
	 * 读取文件时编码
	 * */
	private String readCharset="UTF-8";
	
	/**
	 * 写文件时的编码
	 * */
	private String writeCharset="UTF-8";
	
	public SimpleTxtProcess() {
		
	}
	

	@Override
	public void processSingleFile(String sourceFilePath,String targetFilePath,List<LineHandler> handleList) {
		File file = new File(sourceFilePath);
		File targetFile=new File(targetFilePath);
		TxtReader reader = new SimpleTxtReader(file,readCharset);
//		TxtWriter txtWriter = new SimpleTxtWriter(targetFile);
		TxtWriter txtWriter = new SimpleTxtEncodeWriter(targetFile, writeCharset);
		while (reader.hasNext()) {
			String currentLine = reader.readLine();
			String processLine=currentLine;
			if(handleList!=null){
				for(LineHandler lineHandler:handleList){
					processLine=lineHandler.processLine(processLine);					
				}
			}
			
			txtWriter.writeLine(processLine);
		}
		reader.close();
		txtWriter.close();
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


}
