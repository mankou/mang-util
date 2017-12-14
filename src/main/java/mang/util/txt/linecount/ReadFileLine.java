package mang.util.txt.linecount;

import java.io.File;
import mang.util.txt.reader.SimpleTxtReader;
import mang.util.txt.reader.TxtReader;

/**
 * 通过一行一行读取文件获取文件行数
 */
public class ReadFileLine implements LineCountSta {

	@Override
	public Long getLineCount(String filePath) {
		Long lineCount = 0L;
		File file = new File(filePath);
		TxtReader reader = null;
		try {
			reader = new SimpleTxtReader(file);
			while (reader.hasNext()) {
				lineCount++;
			}
			reader.close();
			return lineCount;
		} catch (Exception e) {
			//如果文件不存在返回null
			return null;
		}

	}

}
