package mang.util.txt.process;

import mang.util.txt.linefilter.LineFilter;

public interface TxtLineFilterProcessor {
	
	/**
	 * 前置过滤 用于在转换行前过滤掉数据
	 * */
	public boolean beforeFilter(String line);
	
	
	
	/**
	 * 后置过过滤 用于在转换完后过滤掉数据
	 * */
	public boolean afterFilter(String line);
	
	
	/**
	 * 添加前置过滤器
	 * */
	public TxtLineFilterProcessor addBeforeFilter(LineFilter lineFilter);
	

	/**
	 * 添加后置过滤器
	 * */
	public TxtLineFilterProcessor addAfterFilter(LineFilter lineFilter);
}
