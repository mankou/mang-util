package mang.util.txt.process;

import java.io.File;
import java.util.List;

import mang.util.txt.linehandle.LineHandler;
import mang.util.txt.reader.SimpleTxtReader;
import mang.util.txt.reader.TxtReader;
import mang.util.txt.writer.SimpleTxtWriter;
import mang.util.txt.writer.TxtWriter;

public class SimpleTxtProcess implements TxtProcess {
	
	/**
	 * 文件编码
	 * */
	private String charset="UTF-8";
	
	public SimpleTxtProcess() {
		
	}
	
	public SimpleTxtProcess(String charset) {
		this();
		setCharset(charset);
	}

	@Override
	public void processSingleFile(String sourceFilePath,String targetFilePath,List<LineHandler> handleList) {
		File file = new File(sourceFilePath);
		File targetFile=new File(targetFilePath);
		TxtReader reader = new SimpleTxtReader(file,charset);
		TxtWriter txtWriter = new SimpleTxtWriter(targetFile);
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

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
