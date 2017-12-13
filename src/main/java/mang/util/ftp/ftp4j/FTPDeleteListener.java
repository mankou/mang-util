package mang.util.ftp.ftp4j;

public interface FTPDeleteListener {
	
	/**
	 * 文件删除后的处理操作
	 * */
	public void afterDeleteFile(String deletePath);
}
