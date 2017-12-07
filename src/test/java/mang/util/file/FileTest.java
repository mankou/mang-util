package mang.util.file;

import java.io.File;
import org.junit.Test;

import mang.util.common.FileUtil;

public class FileTest {

	@Test
	public void testRename() {
		String filePath = "c:/test/shenlongdxp/testtxt/record.txt";
		String filePath2 = "c:/test/shenlongdxp/testtxt/record-rename.txt";
		File file = new File(filePath);
		file.renameTo(new File(filePath2));
	}

	@Test
	public void testRename2() {
		String filePath = "c:/test/data/test1.txt";
		String filePath2 = "c:/test/data/test1-rename.txt";
		FileUtil.renameFile(filePath, filePath2);
	}

	@Test
	public void testMoveToDir() {
		String dirPath = "c:/test/data/";
		String targetPath = "c:/test/data2/";
		FileUtil.moveFoldFilesToDirectory(dirPath, targetPath);

	}
}
