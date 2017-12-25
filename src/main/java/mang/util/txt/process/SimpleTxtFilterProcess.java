package mang.util.txt.process;

import java.util.List;

import mang.util.txt.linefilter.LineFilter;
import mang.util.txt.linehandle.LineHandler;

public class SimpleTxtFilterProcess extends AbstractTxtFilterProcess {
	
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

	@Override
	public boolean beforeFilter(String line, List<LineFilter> filterList) {
		return filter(line, filterList);
	}

	@Override
	public boolean afterFilter(String line, List<LineFilter> filterList) {
		return filter(line, filterList);
	}
	
	
	public boolean filter(String line, List<LineFilter> filterList) {
		if(filterList!=null &&filterList.size()>0){
			for(LineFilter lineFilter:filterList){
				if(lineFilter.isFilter(line)){
					return true;
				}
			}
		}
		return false;
	}
	
	
}
