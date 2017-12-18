package mang.util.encode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

/**
 * 文件编码检测
 */
public class CharsetDetector {
	/*
	 * 代码来自 jchardet下载的zip中的样例代码 HtmlCharsetDetector
	 * 而且参考了网上下载的 FileCharsetDetector
	 * */
	
	public static boolean found = false;
	private static String encoding;
	
	
	/**
	 * 检测文件编码
	 * @param filePath 文件路径
	 * @return String 文件编码
	 * */
	public static String checkCharset(String filePath){
		File file=new File(filePath);
		if(!file.exists()||file.isDirectory()){
			return null;
		}
		String encode=checkCharset(file);
		return encode;
		
	}

	/**
	 * 检测文件编码
	 * @param file 文件
	 * @return String 文件编码
	 * */
	public static String checkCharset(File file) {
		nsDetector det = new nsDetector();

		det.Init(new nsICharsetDetectionObserver() {
			public void Notify(String charset) {
				CharsetDetector.found = true;
				encoding = charset;
				System.out.println("CHARSET = " + charset);
			}
		});

		FileInputStream fileInputStream=null;
		BufferedInputStream imp=null;
		try {
			fileInputStream = new FileInputStream(file);
			imp = new BufferedInputStream(fileInputStream);

			byte[] buf = new byte[1024];
			int len;
			boolean done = false;
			boolean isAscii = true;

			while ((len = imp.read(buf, 0, buf.length)) != -1) {

				// Check if the stream is only ascii.
				if (isAscii)
					isAscii = det.isAscii(buf, len);

				// DoIt if non-ascii and not done yet.
				if (!isAscii && !done)
					done = det.DoIt(buf, len, false);
			}
			det.DataEnd();

			if (isAscii) {
				System.out.println("CHARSET = ASCII");
				found = true;
				encoding = "ASCII";
			}

			if (!found) {
				String prob[] = det.getProbableCharsets();
				for (int i = 0; i < prob.length; i++) {
					System.out.println("Probable Charset = " + prob[i]);
				}

				/*
				 * 如果没有找到 就从可能的列表中取第1个
				 * 这句是从FileCharsetDetector中看到
				 * 因为我发现如果是utf-8 就会出现找不到编码的情况 但从可能的编码中取第1个就是utf-8
				 */
				if (prob.length > 0) {
					encoding = prob[0];
				} else {
					return null;
				}
			}

			/*
			 * XXX
			 * 如下这句是我加的，因为我发现不加会出现 如下的bug 但因我对观察者模式也没有理解透
			 * 所以也不确定这句有什么意义，但从测试结果看是对的
			 * 
			 * bug描述：如果有多个文件 你循环判断各个文件的编码,如果第一个文件是ansci格式 则后面的几个文件其也会判断成ansci格式
			 * 我感觉与这里使用的观察者模式有关
			 */
			found = false;
			return encoding;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				imp.close();
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
