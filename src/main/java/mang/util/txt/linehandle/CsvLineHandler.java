package mang.util.txt.linehandle;

import org.apache.commons.lang3.StringUtils;

public class CsvLineHandler implements LineHandler {
	private String oldSpliter="||";
	private String newSpliter=",";
	
	public void CsvLineHandler(){
		
	}

	@Override
	public String processLine(String line) {
		//将分隔符 处理成新分隔符	
		String newLine=StringUtils.replace(line, oldSpliter, newSpliter);
		return newLine;
	}

	public String getOldSpliter() {
		return oldSpliter;
	}

	public void setOldSpliter(String oldSpliter) {
		this.oldSpliter = oldSpliter;
	}

	public String getNewSpliter() {
		return newSpliter;
	}

	public void setNewSpliter(String newSpliter) {
		this.newSpliter = newSpliter;
	}

}
