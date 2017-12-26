package mang.util.txt.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleTxtWriter implements TxtWriter {

	private FileWriter fileWriter;

	private BufferedWriter bufferWriter;
	
	private int writeCount=0;

	public SimpleTxtWriter(File file) {
		this(file, false);
	}

	public SimpleTxtWriter(File file, boolean isAppend) {
		try {
			fileWriter = new FileWriter(file, isAppend);
			bufferWriter = new BufferedWriter(fileWriter);
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

		if (fileWriter != null) {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getWriteCount() {
		return writeCount;
	}
	
	

}
