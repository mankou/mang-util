package mang.util.txt;

import mang.util.txt.linehandle.LineHandler;

public class SimpleTxtHandle implements TxtHandle {

	private String filePath;
	
	
	
	@Override
	public void init() {
		

	}

	@Override
	public String readLine() {
		return null;
	}

	@Override
	public String processLine(String line, LineHandler lineHandle) {
		String processLine=lineHandle.processLine(line);
		return processLine;
	}

	@Override
	public void finish() {


	}

	@Override
	public boolean hasNext() {
		return false;
	}

}
