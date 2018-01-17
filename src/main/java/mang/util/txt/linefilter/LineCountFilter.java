package mang.util.txt.linefilter;

/**
 * 提供一个可统计处理了多少行的LineFilter实现
 * */
public abstract class LineCountFilter extends SimpleAbstractLineFilter {
	
	private int lineCount=0;
	
	private int processLineCount=0;
	
	public abstract boolean isReTain(String line);

	@Override
	public boolean isConform(String line) {
		lineCount=this.getContext().getCurrentLineNum();
		processLineCount++;
		boolean result=isReTain(line);
		return result;
	}

	public int getLineCount() {
		return lineCount;
	}

	public int getProcessLineCount() {
		return processLineCount;
	}


}
