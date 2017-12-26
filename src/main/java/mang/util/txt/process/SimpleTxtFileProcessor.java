package mang.util.txt.process;


public class SimpleTxtFileProcessor extends AbstractTxtProcessor {
	/*
	 * 该类的使用就是注入一些默认的 FilterProcessor和LineHandleProcessor
	 * */
	
	public  SimpleTxtFileProcessor(){
		this.setLineFilterProcessor(new AllTxtLineFilterProcessor());
		this.setLineHandleProcessor(new SimpleLineHandleProcessor());
	}
	
	public  SimpleTxtFileProcessor(TxtLineFilterProcessor lineFilterProcess){
		this.setLineFilterProcessor(lineFilterProcess);
		this.setLineHandleProcessor(new SimpleLineHandleProcessor());
	}
}
