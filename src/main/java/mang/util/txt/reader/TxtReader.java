package mang.util.txt.reader;

public interface TxtReader {
	
	public boolean hasNext();
	
	public String readLine();
	
	public void close();
}
