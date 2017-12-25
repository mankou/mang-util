package mang.util.txt.process;

import java.util.List;

import mang.util.txt.linefilter.LineFilter;
import mang.util.txt.linehandle.LineHandler;

public class AnyTxtFilterProcess extends SimpleTxtFilterProcess {
	
	@Override
	public boolean beforeFilter(String line, List<LineFilter> filterList) {
		return filter(line, filterList);
	}

	@Override
	public boolean afterFilter(String line, List<LineFilter> filterList) {
		return filter(line, filterList);
	}
	
	
	/**
	 * 只要有一个满足规则就 保留
	 * */
	public boolean filter(String line, List<LineFilter> filterList) {
		if(filterList!=null &&filterList.size()>0){
			for(LineFilter lineFilter:filterList){
				if(lineFilter.isConform(line)){
					return true;
				}
			}
			return false;
		}else{
			return true;
		}
	}
	
	
}
