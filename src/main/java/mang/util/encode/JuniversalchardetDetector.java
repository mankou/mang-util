package mang.util.encode;

import java.io.File;
import java.io.FileInputStream;

import org.mozilla.universalchardet.UniversalDetector;

public class JuniversalchardetDetector {
	public static String getFileIncode(File file) {

		if (!file.exists()) {
			System.err.println("getFileIncode: file not exists!");
			return null;
		}

		byte[] buf = new byte[4096];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			// (1)
			UniversalDetector detector = new UniversalDetector(null);

			// (2)
			int nread;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
			// (3)
			detector.dataEnd();

			// (4)
			String encoding = detector.getDetectedCharset();
			if (encoding != null) {
//				System.out.println("Detected encoding = " + encoding);
			} else {
//				System.out.println("No encoding detected.");
			}

			// (5)
			detector.reset();
			fis.close();
			return encoding;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
