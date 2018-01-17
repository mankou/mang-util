package mang.util.txt.process;

import java.util.List;

import mang.util.txt.linefilter.LineFilter;

public class AllTxtLineFilterProcessor extends AbstractLineFilterProcessor {
	
	@Override
	public boolean processBeforeFilter(String line) {
		return filter(line, this.getBeforeLineFilter());
	}

	@Override
	public boolean processAfterFilter(String line) {
		return filter(line, this.getAfterLineFilter());
	}
	
	
	/**
	 * 所有的条件都满足才保留
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
