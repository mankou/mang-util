package mang.util.ftp.ftp4j;

/**
 * 删除文件的监听
 * */
public interface FTPDeleteListener {
	
	/**
	 * 文件删除后的处理操作
	 * @param deletePath 删除路径
	 * */
	public void afterDeleteFile(String deletePath);
}
