package mang.util.txt.process;

import java.util.List;

import mang.util.txt.linefilter.LineFilter;

public class AllTxtFilterProcess extends SimpleTxtFilterProcess {
	
	@Override
	public boolean beforeFilter(String line, List<LineFilter> filterList) {
		return filter(line, filterList);
	}

	@Override
	public boolean afterFilter(String line, List<LineFilter> filterList) {
		return filter(line, filterList);
	}
	
	
	/**
	 * 所有的条件都保存才保留
	 * */
	public boolean filter(String line, List<LineFilter> filterList) {
		if(filterList!=null &&filterList.size()>0){
			for(LineFilter lineFilter:filterList){
				if(!lineFilter.isConform(line)){
					return false;
				}
			}
			return true;
		}else{
			return true;
		}
	}
	
	
}
