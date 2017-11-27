package mang.util.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

public class TextTest {

	/**
	 * 演示读取txt文件
	 * */
	@Test
	public void testReadText() {
		File file = new File("c:/test/test.txt");
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println(result);
	}
}
