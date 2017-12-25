package mang.util.txt.process;

import java.util.List;

import mang.util.txt.linefilter.LineFilter;
import mang.util.txt.linehandle.LineHandler;

public interface TxtFilterProcess {
	
	/**
	 * 前置过滤 用于在转换行前过滤掉数据
	 * */
	public boolean beforeFilter(String line,List<LineFilter> filterList);
	
	
	/**
	 * 行转换
	 * 
	 * */
	public String processLine(String line,List<LineHandler> handleList);
	
	
	/**
	 * 后置过过滤 用于在转换完后过滤掉数据
	 * */
	public boolean afterFilter(String line,List<LineFilter> filterList);
}
