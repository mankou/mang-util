package mang.util.ftp.ftp4j;

public interface FTPDownListener {
	public void afterDownload(String remoteFilePath,String localDirpath);
	
	public int  getDownloadCount();
}
