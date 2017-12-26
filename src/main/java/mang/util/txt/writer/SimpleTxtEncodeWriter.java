package mang.util.txt.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 文件输出类
 * 可以指定文件编码
 * */
public class SimpleTxtEncodeWriter implements TxtWriter {

	private OutputStreamWriter outputStreamWriter;

	private BufferedWriter bufferWriter;
	
	private String charset;
	
	private int writeCount=0;

	public SimpleTxtEncodeWriter(File file) {
		this(file, false,"UTF-8");
	}
	
	public SimpleTxtEncodeWriter(File file,String charset) {
		this(file, false,charset);
	}

	public SimpleTxtEncodeWriter(File file, boolean isAppend,String charset) {
		try {
			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
			bufferWriter = new BufferedWriter(outputStreamWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeLine(String content) {
		try {
			bufferWriter.write(content);
			bufferWriter.newLine();
			bufferWriter.flush();
			writeCount++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		if (bufferWriter != null) {
			try {
				bufferWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (outputStreamWriter != null) {
			try {
				outputStreamWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getWriteCount() {
		return writeCount;
	}
	
	


}
