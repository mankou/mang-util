package mang.util.txt.linefilter;

import mang.util.txt.process.TxtProcessContext;

public class SimpleAbstractLineFilter implements LineFilter {
	
	private TxtProcessContext context;

	@Override
	public boolean isConform(String line) {
		
		return false;
	}

	@Override
	public void setContext(TxtProcessContext context) {
		this.context=context;
	}
	
	
	public TxtProcessContext getContext(){
		return context;
	}

}
