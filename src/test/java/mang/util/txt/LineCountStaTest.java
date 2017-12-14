package mang.util.txt;

import org.junit.Test;

import mang.util.txt.linecount.LineCountSta;
import mang.util.txt.linecount.ReadFileLine;
import mang.util.txt.linecount.ReadFileLineIgnoreBlankLine;

public class LineCountStaTest {
	
	@Test
	public void testReadFileLineCount(){
		String filePath="c:/20100205pvnotas03p.txt";
		LineCountSta lineCountSta=new ReadFileLine();
		Long lineCount=lineCountSta.getLineCount(filePath);
		System.out.println(lineCount);
	}
	
	@Test
	public void testReadFileLineIgnoreBlankLine(){
		String filePath="c:/20100205pvnotas03p.txt";
		LineCountSta lineCountSta=new ReadFileLineIgnoreBlankLine();
		Long lineCount=lineCountSta.getLineCount(filePath);
		System.out.println(lineCount);
	}
	
	
}
