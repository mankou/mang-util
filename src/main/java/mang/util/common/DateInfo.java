package mang.util.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/** 
 * 日期信息.
 * 方便取出日期年份 季度 月份 天的类
 * @author mang
 * @version
 * create:2015-8-25 14:49:05
 * modify:2015-8-25 14:49:10
 */
public class DateInfo {
	private Timestamp currentTime;
	private String year;
	private String quarter;
	private String month;
	private String day;
	
	/** 
	 * 以当前时间计算.
	 */
	public DateInfo(){
		this.currentTime=TimeUtil.getCurrentTime();
	}
	
	/** 
	 * 传入一个时间计算.
	 */
	public DateInfo(Timestamp time){
		if(time!=null){
			this.currentTime=time;
		}else{
			this.currentTime=TimeUtil.getCurrentTime();
		}
	}
	
	public DateInfo parseData(){
		Long time = currentTime.getTime();
		SimpleDateFormat dfyear = new SimpleDateFormat("yyyy");  
		year=dfyear.format(time);
		
		SimpleDateFormat dfmonth = new SimpleDateFormat("MM");  
		month=dfmonth.format(time);
		
		//
		if("01".equals(month)
			||"02".equals(month)
			||"03".equals(month)){
			quarter="1";
		}else if("04".equals(month)
				|| "05".equals(month)
				|| "06".equals(month)){
			quarter="2";
		}else if("07".equals(month)
				||"08".equals(month)
				||"09".equals(month)){
			quarter="3";
		}else{
			quarter="4";
		}
		
		
		SimpleDateFormat dfday = new SimpleDateFormat("dd");  
		day=dfday.format(time);

		
		return this;
	}
	
	public Timestamp getCurrentTime() {
		return currentTime;
	}
	
	private void setCurrentTime(Timestamp currentTime) {
		this.currentTime = currentTime;
	}
	
	/** 
	 * 获取年份.
	 * <p>使用方法:<br>
	 * DateInfo dateinfo = new DateInfo(); 或者Date info dateinfo=new DateInfo(t); <br>
	 * dateinfo.parseData()
	 * String yeqr = dateinfo.getYear();
	 */
	public String getYear() {
		return year;
	}
	
	private void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * 获取季度.
	 * <p>使用方法:<br>
	 * DateInfo dateinfo = new DateInfo(); 或者Date info dateinfo=new DateInfo(t); <br>
	 * dateinfo.parseData()
	 * String yeqr = dateinfo.getQuarter();
	 */
	public String getQuarter() {
		return quarter;
	}
	
	private void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	/**
	 * 获取月份.
	 * <p>使用方法:<br>
	 * DateInfo dateinfo = new DateInfo(); 或者Date info dateinfo=new DateInfo(t); <br>
	 * dateinfo.parseData()
	 * String yeqr = dateinfo.getMonth();
	 */
	public String getMonth() {
		return month;
	}


	private void setMonth(String month) {
		this.month = month;
	}

	/** 
	 * 获取天.
	 * <p>使用方法:<br>
	 * DateInfo dateinfo = new DateInfo(); 或者Date info dateinfo=new DateInfo(t); <br>
	 * dateinfo.parseData()
	 * String yeqr = dateinfo.getDay();
	 */
	public String getDay() {
		return day;
	}
	
	private void setDay(String day) {
		this.day = day;
	}
	
}
