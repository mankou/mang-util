package mang.util.txt.process;

import java.util.List;

import mang.util.txt.linefilter.LineFilter;

public class AnyTxtLineFilterProcessor extends AbstractLineFilterProcessor {
	
	@Override
	public boolean processBeforeFilter(String line) {
		return filter(line, this.getBeforeLineFilter());
	}

	@Override
	public boolean processAfterFilter(String line) {
		return filter(line, this.getAfterLineFilter());
	}
	
	
	/**
	 * 只要有一个满足规则就保留
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
