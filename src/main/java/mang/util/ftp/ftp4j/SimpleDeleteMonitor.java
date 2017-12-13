package mang.util.ftp.ftp4j;

public class SimpleDeleteMonitor implements FTPDeleteListener {
	
	private int deleteCount=0;

	@Override
	public void afterDeleteFile(String deletePath) {
		deleteCount++;
	}

	public int getDeleteCount() {
		return deleteCount;
	}


}
