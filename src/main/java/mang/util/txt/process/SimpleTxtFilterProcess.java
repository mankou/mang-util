package mang.util.txt.process;

import java.util.List;
import mang.util.txt.linehandle.LineHandler;

public abstract class SimpleTxtFilterProcess extends AbstractTxtFilterProcess {
	
	@Override
	public String processLine(String line, List<LineHandler> handleList) {
		String processLine=line;
		if(handleList!=null&&handleList.size()>0){
			for(LineHandler lineHandler:handleList){
				processLine=lineHandler.processLine(processLine);
			}
		}
		return processLine;
	}	
	
}
