package mang.util.txt.process;

import org.junit.Test;

import mang.util.txt.linefilter.BlankFilter;
import mang.util.txt.linehandle.CommaLineHandler;

public class SimpleTxtFilterProcessTest {
	
	@Test
	public void testSingleFile(){
		String sourceFilePath="c:/test/data/test1.txt";
		String targetFilePath="c:/test/data/test2.csv";
		
		processSingleFile(sourceFilePath, targetFilePath);
		
	}
	
	private void processSingleFile(String sourceFilePath,String targetFilePath){	
		AbstractTxtFilterProcess simpleProcess=new AnyTxtFilterProcess();
		simpleProcess.addHandle(new CommaLineHandler());
		simpleProcess.addBeforeFilter(new BlankFilter());
		simpleProcess.processSingleFile(sourceFilePath, targetFilePath);
	}
}
