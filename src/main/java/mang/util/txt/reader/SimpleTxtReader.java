package mang.util.txt.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class SimpleTxtReader implements TxtReader {

	private FileInputStream fileInputStream;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private String currentLine;

	
	public SimpleTxtReader(File file, String charset) {
		try {
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream, charset);
			bufferedReader = new BufferedReader(inputStreamReader);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public SimpleTxtReader(File file) {
		this(file, "UTF-8");
	}

	@Override
	public boolean hasNext() {
		try {
			currentLine = bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		if (currentLine != null) {
			return true;
		}
		return false;
	}

	@Override
	public String readLine() {
		return currentLine;
	}

	@Override
	public void close() {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
			}
		}
		if (inputStreamReader != null) {
			try {
				inputStreamReader.close();
			} catch (IOException e) {
				
			}
		}
		if (fileInputStream != null) {
			try {
				fileInputStream.close();
			} catch (IOException e) {
			}
		}
	}

}
