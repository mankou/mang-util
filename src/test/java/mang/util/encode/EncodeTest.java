package mang.util.encode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;

import mang.util.encode.FileCharsetDetector;

public class EncodeTest {

	private String[] paths = new String[] { 
											"c:/test/testencode/ansi.txt",
											"c:/test/testencode/utf-8.txt",
											"c:/test/testencode/utf-8nobom.txt",
											"c:/test/testencode/unicode.txt",
											"c:/test/testencode/gb18080.txt",
										};
	
	
	/*最终决定使用  我自己的开发的类 CharsetDetector
	 * 原因:肯定要推自己的代码
	 * 实际上我的CharsetDetector 和FileCharsetDetector代码原理是一样的，都来自于jchardet，我只是解决了第一个文件是ansi的bug而已
	 * 实际上FileCharsetDetector功能更多，但我也不想修改他的bug,该文件先保留做为自己开发代码的参考
	 * 
	 * 不使用 FileCharsetDetector的原因 
	 * 原因: jchartdet 如果第一个文件是ansi，则其识别的第2个文件也是ansi格式 我怀疑与其观察者模式有关  就凭这一点就不能用它
	 * 
	 * 不使用Juniversalchardet的原因
	 * 原因1：其不能识别ANSCII文件的编码
	 * 原因2：其代码也是从网上找到，反正我也看不懂
	 * */

	
	/* juniv
	 * ASCII null
	 * UTF-8  UTF-8
	 * utf-8nobom null
	 * unicode UTF-16LE
	 * GB18030 GB18030
	 */
	
	
	/* jchartdet
	 * ASCII ASCII
	 * UTF-8  UTF-8
	 * utf-8nobom ASCII
	 * unicode windows-1252
	 * GB18030 GB2312
	 */
	
	
	
	@Test
	public void testContrast() throws FileNotFoundException, IOException, InterruptedException {

		for (String path : paths) {
			System.out.println("文件路径:" + path);
			File file = new File(path);
			
			String encode_juniv = JuniversalchardetDetector.getFileIncode(file);
			System.out.println("juniversalchardet检测结果:" + encode_juniv);
//			
			String encode_jchartdet = FileCharsetDetector.checkEncoding(path);
			System.out.println("JCharDet检测结果:" + encode_jchartdet);
			
			
			String encode_my=CharsetDetector.checkCharset(file);
			System.out.println("encode_my检测结果:" + encode_my);
			System.out.println();
		}

	}

	
	@Test
	public void juniversalchardet() {
		String path = "";
		
		File file = new File(path);
		String encode = JuniversalchardetDetector.getFileIncode(file);
		System.out.println("juniversalchardet检测结果:" + encode);
	}

	@Test
	public void testJCharDet() throws FileNotFoundException, IOException {
		String path = "c:/test/testencode/utf-8.txt";
		
		File file = new File(path);
		String encode = FileCharsetDetector.checkEncoding(file);
		System.out.println("JCharDet检测结果:" + encode);
	}
	
	@Test
	public void testMy() throws IOException {
//		String path = "/test/data/20100205pvnotas03p.txt";
		String path = "c:/test/data/modelsDict_7406PCS_20171208163221.txt";
		File file = new File(path);
		String encode = CharsetDetector.checkCharset(file);
		System.out.println("my检测结果:" + encode);
	}
	
	
	/*
	 * ASCII文件生成方式:windows上用词本新建一文件 保存时选择ASCII编码
	 * UTF-8文件生成方式: 用ue新建一文件 保存时选择UTF-8编码
	 * unicode 用editplsu 新建一文件 保存时选择unicode编码
	 * gb18080 随便找的文件 用vim打开 看编码是gb18080
	 */

}
