package mang.util.txt.process;

import java.util.List;

import mang.util.txt.linehandle.LineHandler;

public interface TxtProcess {
	public void processSingleFile(String sourceFilePath,String targetFilePath,List<LineHandler> handleList);
}
