package mang.util.txt.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import mang.util.txt.linehandle.CommaLineHandler;
import mang.util.txt.linehandle.CsvLineHandler;
import mang.util.txt.linehandle.LineHandler;
import mang.util.txt.process.SimpleTxtProcess;
import mang.util.txt.process.TxtProcess;

public class TxtProcessTest {
	
	@Test
	public void testSingleFile(){
		String sourceFilePath="c:/data/test1.txt";
		String targetFilePath="c:/data/test2.csv";
		
		processSingleFile(sourceFilePath, targetFilePath);
		
	}
	
	private void processSingleFile(String sourceFilePath,String targetFilePath){	
		TxtProcess txtProcess=new SimpleTxtProcess();
		List<LineHandler> handleList=new ArrayList<LineHandler>();
		handleList.add(new CommaLineHandler());
		handleList.add(new CsvLineHandler());
		txtProcess.processSingleFile(sourceFilePath, targetFilePath, handleList);
	}
	
	
	@Test
	public void testDir(){
		String sourceDir="c:/test/data/";
		String destDir="c:/test/datacsv/";
		
		File sourceFold=new File(sourceDir);
		String[] files=sourceFold.list();
		for(String fileName:files){
			String simpleFileName=StringUtils.substringBefore(fileName, ".");
			String sourceFilePath=sourceDir+File.separator+fileName;
			String targetFilePath=destDir+File.separator+simpleFileName+".csv";
			this.processSingleFile(sourceFilePath, targetFilePath);
		}
		
	}
}
