package mang.util.txt.linecount;

/**
 * 获取文件行数的接口
 * */
public interface LineCountSta {
	
	/**
	 * 获取文件行数
	 * @param filePath 文件路径
	 * @return Long 文件行数 如果文件不存在则返回null 否则返回文件行数
	 * */
	public Long getLineCount(String filePath);
}
