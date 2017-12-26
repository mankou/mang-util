package mang.util.txt.process;

import java.util.ArrayList;
import java.util.List;

import mang.util.txt.linehandle.LineHandler;

public class SimpleLineHandleProcessor implements TxtLineProcessor{
	
	private List<LineHandler> handleList=new ArrayList<LineHandler>();
	
	private int lineCount;

	@Override
	public String processLine(String line) {
		String processLine=line;
		if(handleList!=null&&handleList.size()>0){
			for(LineHandler lineHandler:handleList){
				processLine=lineHandler.processLine(processLine);
			}
		}
		lineCount++;
		return processLine;
	}
	
	
	public SimpleLineHandleProcessor addHandler(LineHandler lineHandler){
		this.handleList.add(lineHandler);
		return this;
	}


	@Override
	public int getProcessCount() {
		return lineCount;
	}

}
