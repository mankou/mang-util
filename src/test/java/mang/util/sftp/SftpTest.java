package mang.util.sftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import org.junit.Test;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

import mang.util.common.DateUtil;

public class SftpTest {

	private static Sftp getSftp() {
		String host = "10.4.122.106";
		int port = 22;
		int timeout = 60000;
		String username = "shenlong";
		String password = "shenlong";
		Sftp sftp = new Sftp(host, port, timeout, username, password);

		return sftp;
	}


	@Test
	public void testLogin() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		String pwd=sftp.pwd();
		System.out.println(pwd);
		sftp.changeToHomeDir();
		String homeDir=sftp.pwd();
		System.out.println(homeDir);
		
		sftp.changeDir("/home/shenlong");
		String currentDir=sftp.pwd();
		System.out.println(currentDir);
		sftp.logout();
	}

	/**
	 * 发现不能 channel.mkdir("test2/test2"); 不行 即使上一级目录存在也不行
	 * 
	 */
	@Test
	public void testMakeDir() { // OK
		Sftp sftp = getSftp();

		sftp.login();
		// sftp.makeDir("test2");
		// sftp.changeDir("test2");
		// sftp.makeDir("test2/test2_1");
		ChannelSftp channel = sftp.getChannel();
		// channel.mkdir("/home/test/test2/test2_1/");
		try {
			channel.mkdir("test2/test2");
		} catch (SftpException e) {
			e.printStackTrace();
		}
		sftp.logout();
	}

	@Test
	public void testDelFile() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.delFile("file1.txt");
		sftp.logout();
	}

	@Test
	public void testDelEmptyDir() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.delDir("test3");
		sftp.logout();
	}

	@Test
	public void testLs() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		System.out.println(Arrays.toString(sftp.ls()));
		System.out.println(Arrays.toString(sftp.lsFiles()));
		System.out.println(Arrays.toString(sftp.lsDirs()));
		sftp.logout();
	}

	@Test
	public void testExist() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		System.out.println(sftp.exist("2fs.docx"));
		System.out.println(sftp.exist("test1"));
		System.out.println(sftp.existDir("test2"));
		System.out.println(sftp.existDir("2sfs.txt"));
		System.out.println(sftp.existFile("2sfs.txt"));
		System.out.println(sftp.existFile("test2"));
		sftp.logout();
	}

	@Test
	public void testParamExist() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		System.out.println(sftp.exist("test1", "test4"));
		System.out.println(sftp.exist("test1", "test_bak.jpg"));
		System.out.println(sftp.existDir("/test1", "test3"));
		System.out.println(sftp.existDir("/test1", "test_bak.jpg"));
		System.out.println(sftp.existFile("test1", "test_bak.jpg"));
		System.out.println(sftp.existFile("test1", "test2"));
		sftp.logout();
	}

	@Test
	public void testUploadFile() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.uploadFile("/home/test/test2", "data_jjyl.sql.bak", "c:\\data_jjyl.sql");
		try {
			sftp.uploadFile("/home/test/test2", "data_jjyl.sql.bak2", new FileInputStream("c:\\data_jjyl.sql"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sftp.logout();
	}
	
	@Test
	public void testUploadFile2(){
		Sftp sftp = getSftp();
		sftp.login();
		ChannelSftp  channel= sftp.getChannel();
		try {
			channel.put("c:/testPut/", "/home/test/");
		} catch (SftpException e) {
			e.printStackTrace();
		}
		
		sftp.logout();
	}

	
	@Test
	public void testDownload() { // OK
		Sftp sftp = getSftp();
		sftp.login();
		sftp.downloadFile("test1", "test_bak.txt", "c:\\");
		sftp.downloadFile("/home/test/test1", "test_bak.txt", "c:\\");
		sftp.logout();
	}
	
	@Test
	public void testMkdrip() { // NO
		Sftp sftp = getSftp();
		sftp.login();
		sftp.mkdirp("test3/test4");
		sftp.logout();
	}
	
	
	@Test
	public void testRename() { // NO
		Sftp sftp = getSftp();
		sftp.login();
		ChannelSftp  channel=sftp.getChannel();
		try {
			channel.rename("test1/test_bak.txt", "test2/test2");
		} catch (SftpException e) {
			e.printStackTrace();
		}
		sftp.logout();
	}
	
	/**
	 * 测试判断是否有文件 并且取文件 并且移动文件到另一个路径下
	 * */
	@Test
	public void testLsAndGetAndRename(){
		String dst="c:/testget";
		String src="test1";
		String remove="remove";
		
		Sftp sftp = getSftp();
		sftp.login();
		
		String dateStr=DateUtil.getCurrentDateString("yyyyMMddHHmmss");
		String realRemovePath=remove+"/"+dateStr;
		sftp.mkdirp(realRemovePath);
		
		ChannelSftp channel=sftp.getChannel();
		String[] files=sftp.ls(src);
		if(files!=null && files.length>0){
			for(String fileName:files){
				System.out.println(fileName);
				sftp.get(src+"/"+fileName, dst);
				sftp.rename(src+"/"+fileName, realRemovePath+"/"+fileName);
			}
		}else{
			System.out.println("没有文件");
		}
		
		sftp.logout();
		
	}
	
	
	

}
