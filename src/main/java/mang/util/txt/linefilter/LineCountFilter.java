package mang.util.txt.linefilter;

public abstract class LineCountFilter implements LineFilter {
	
	private int lineCount=0;
	
	public abstract boolean isReTain(String line);

	@Override
	public boolean isConform(String line) {
		lineCount++;
		boolean result=isReTain(line);
		return result;
	}

	public int getLineCount() {
		return lineCount;
	}

}
