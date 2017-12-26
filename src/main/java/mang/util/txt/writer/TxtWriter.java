package mang.util.txt.writer;

public interface TxtWriter {
	public void writeLine(String content);
	
	public void close();
	
	
	/**
	 * 获取写的行数
	 * */
	public int getWriteCount();
}
