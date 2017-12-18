package mang.util.txt.writer;

import static org.junit.Assert.assertFalse;

import java.io.File;
import org.junit.Test;
import mang.util.encode.CharsetDetector;

public class SimpleTxtEncodeWriterTest {
	
	@Test
	public void test(){
		String encode_GB18030="GB18030";
		String encode_UTF_8="UTF-8";
		String encode_GB2312="GB2312";
		
		File file=new File("c:/test/test-gb18080.txt");
		TxtWriter txtWriter=new SimpleTxtEncodeWriter(file,encode_GB18030);
		txtWriter.writeLine("中国");
		txtWriter.writeLine("china");
		txtWriter.writeLine("中华");
		txtWriter.writeLine("china hua");
		txtWriter.close();
		
		String encode1=CharsetDetector.checkCharset(file);
		System.out.println("GB18080的检测结果是:"+encode1);
		if(!encode1.equals(encode_GB18030) && !encode1.equals("GB2312")){
			assertFalse(true);
		}
		
		
		File file2=new File("c:/test/test-gb2312.txt");
		TxtWriter txtWriter2=new SimpleTxtEncodeWriter(file2,encode_GB2312);
		txtWriter2.writeLine("中国");
		txtWriter2.writeLine("china");
		txtWriter2.writeLine("中华");
		txtWriter2.writeLine("china hua");
		txtWriter2.close();
		
		String encode2=CharsetDetector.checkCharset(file2);
		System.out.println("GB2312的检测结果是:"+encode2);
		if(!encode2.equals(encode_GB18030) && !encode2.equals(encode_GB2312)){
			assertFalse(true);
		}
		
		
		File file3=new File("c:/test/test-utf-8.txt");
		TxtWriter txtWriter3=new SimpleTxtEncodeWriter(file3,encode_UTF_8);
		txtWriter3.writeLine("中国");
		txtWriter3.writeLine("china");
		txtWriter3.writeLine("中华");
		txtWriter3.writeLine("china hua");
		txtWriter3.close();
		
		String encode3=CharsetDetector.checkCharset(file3);
		System.out.println("UTF-8的检测结果是:"+encode3);
		if(!encode3.equals(encode_UTF_8)){
			assertFalse(true);
		}
	}
}
