package mang.util.txt.process;

import org.junit.Test;

import mang.util.txt.linefilter.BlankFilter;
import mang.util.txt.linehandle.CommaLineHandler;

public class SimpleTxtFilterProcessTest {
	
	private String sourceFilePath="c:/test/data/test1.txt";
	private String targetFilePath="c:/test/data/test2.csv";
	

	@Test
	public void test1(){	
		AbstractTxtProcessor txtFileProcess=new SimpleTxtFileProcessor();
		txtFileProcess.getLineFilterProcessor().addBeforeFilter(new BlankFilter());
		txtFileProcess.getLineHandleProcessor().addHandler(new CommaLineHandler());
		txtFileProcess.processSingleFile(sourceFilePath, targetFilePath);
		int count=txtFileProcess.getLineHandleProcessor().getProcessCount();
		System.out.println("处理行数:"+count);
	}
	
	
	@Test
	public void test2(){
		//这里多了2句 用于演示设置linehandle和linefilter
		AbstractTxtProcessor txtFileProcess=new AbstractTxtProcessor();
		txtFileProcess.setLineHandleProcessor(new SimpleLineHandleProcessor());
		txtFileProcess.setLineFilterProcessor(new AllTxtLineFilterProcessor());
		txtFileProcess.getLineFilterProcessor().addBeforeFilter(new BlankFilter());
		txtFileProcess.getLineHandleProcessor().addHandler(new CommaLineHandler());
		txtFileProcess.processSingleFile(sourceFilePath, targetFilePath);
		int count=txtFileProcess.getLineHandleProcessor().getProcessCount();
		System.out.println("处理行数:"+count);
	}
}
