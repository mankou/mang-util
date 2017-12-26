package mang.util.txt.reader;

public interface TxtReader {
	
	public boolean hasNext();
	
	public String readLine();
	
	public void close();
	
	/**
	 * 获取读取多少行
	 * 
	 * @return int 读取了多少行
	 * */
	public int getReadCount();
}
