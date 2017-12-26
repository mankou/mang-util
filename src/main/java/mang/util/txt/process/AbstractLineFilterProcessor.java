package mang.util.txt.process;

import java.util.ArrayList;
import java.util.List;
import mang.util.txt.linefilter.LineFilter;

public abstract class AbstractLineFilterProcessor implements TxtLineFilterProcessor {

	@Override
	public abstract boolean beforeFilter(String line);
	
	@Override
	public abstract boolean afterFilter(String line);
	
	
	private List<LineFilter> beforeLineFilter=new ArrayList<LineFilter>();

	
	private List<LineFilter> afterLineFilter=new ArrayList<LineFilter>();
	
	
	public AbstractLineFilterProcessor addBeforeFilter(LineFilter lineFilter){
		this.beforeLineFilter.add(lineFilter);
		return this;
	}
	
	public AbstractLineFilterProcessor addAfterFilter(LineFilter lineFilter){
		this.afterLineFilter.add(lineFilter);
		return this;
	}

	public List<LineFilter> getBeforeLineFilter() {
		return beforeLineFilter;
	}

	public void setBeforeLineFilter(List<LineFilter> beforeLineFilter) {
		this.beforeLineFilter = beforeLineFilter;
	}

	public List<LineFilter> getAfterLineFilter() {
		return afterLineFilter;
	}

	public void setAfterLineFilter(List<LineFilter> afterLineFilter) {
		this.afterLineFilter = afterLineFilter;
	}
}
