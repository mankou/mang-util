package mang.util.txt;

import mang.util.txt.linehandle.LineHandler;

public interface TxtHandle {
	public void init();
	
	public boolean hasNext();
	
	public String readLine();
	
	public String processLine(String line,LineHandler lineHandle);
	
	public void finish();
	
}
