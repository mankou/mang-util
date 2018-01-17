package mang.util.txt.linefilter;

import mang.util.txt.process.TxtProcessContext;

public interface LineFilter {
	/**
	 * 是否符合规则 true表示保留 false表示过滤掉
	 * */
	public boolean isConform(String line);
	
	
	public void setContext(TxtProcessContext context);
}
