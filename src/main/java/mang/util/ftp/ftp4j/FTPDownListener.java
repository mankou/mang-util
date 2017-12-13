package mang.util.ftp.ftp4j;

/**
 * 下载文件的监听
 * */
public interface FTPDownListener {
	public void afterDownload(String remoteFilePath,String localDirpath);
	
	public int  getDownloadCount();
}
