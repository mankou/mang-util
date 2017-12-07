package mang.util.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.junit.Test;

import mang.util.txt.SimpleTxtHandle;
import mang.util.txt.TxtHandle;
import mang.util.txt.linehandle.LineHandler;
import mang.util.txt.linehandle.SimpleLineHandler;
import mang.util.txt.reader.SimpleTxtReader;
import mang.util.txt.reader.TxtReader;
import mang.util.txt.writer.SimpleTxtWriter;
import mang.util.txt.writer.TxtWriter;

public class TXTTest {

	@Test
	public void test() {
		TxtHandle txtHandle = new SimpleTxtHandle();
		LineHandler lineHandler = new SimpleLineHandler();
		txtHandle.init();
		while (txtHandle.hasNext()) {
			String currentLne = txtHandle.readLine();
			String processLine = txtHandle.processLine(currentLne, lineHandler);
			System.out.println(processLine);
		}
		txtHandle.finish();
	}

	@Test
	public void testReadTxt() {
		File file = new File("c:/data/test1.txt");
		String charset = "UTF-8";

		// 设置默认编码
		if (charset == null) {
			charset = "UTF-8";
		}
		if (file.isFile() && file.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				StringBuffer sb = new StringBuffer();
				String text = null;
				while ((text = bufferedReader.readLine()) != null) {
					System.out.println(text);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testWrite() {
		try {
			String content = "测试使用字符串";
			File file = new File("c:/test1.txt");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			fw.close();
			System.out.println("test1 done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTxtReaderAndWriter() {
		File file = new File("c:/data/test1.txt");
		File targetFile=new File("c:/data/test2.txt");
		TxtReader reader = new SimpleTxtReader(file);
		TxtWriter txtWriter = new SimpleTxtWriter(targetFile);
		while (reader.hasNext()) {
			String currentLine = reader.readLine();
			System.out.println(currentLine);
			txtWriter.writeLine(currentLine);
		}
		reader.close();
		txtWriter.close();
	}

}
