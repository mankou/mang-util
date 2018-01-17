package mang.util.txt.process;

import java.util.ArrayList;
import java.util.List;
import mang.util.txt.linefilter.LineFilter;

public abstract class AbstractLineFilterProcessor implements TxtLineFilterProcessor {

	@Override
	public boolean beforeFilter(String line) {
		setContext(this.getBeforeLineFilter());
		return this.processBeforeFilter(line);
	}

	@Override
	public boolean afterFilter(String line) {
		setContext(this.getAfterLineFilter());
		return this.processAfterFilter(line);
	}

	public void setContext(List<LineFilter> filterList) {
		TxtProcessContext context = this.getContext();
		if(filterList!=null && filterList.size()>0){
			for(LineFilter lineFilter:filterList){
				lineFilter.setContext(context);
			}
		}
	}

	private TxtProcessContext context;

	private List<LineFilter> beforeLineFilter = new ArrayList<LineFilter>();

	private List<LineFilter> afterLineFilter = new ArrayList<LineFilter>();

	public abstract boolean processBeforeFilter(String line);

	public abstract boolean processAfterFilter(String line);

	public AbstractLineFilterProcessor addBeforeFilter(LineFilter lineFilter) {
		this.beforeLineFilter.add(lineFilter);
		return this;
	}

	public AbstractLineFilterProcessor addAfterFilter(LineFilter lineFilter) {
		this.afterLineFilter.add(lineFilter);
		return this;
	}

	public void setContext(TxtProcessContext context) {
		this.context = context;
	}

	public TxtProcessContext getContext() {
		return this.context;
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
