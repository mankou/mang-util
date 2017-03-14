package mang.util.common;


import java.io.IOException;

public class RunCmd {
	
	public static void main(String[] args) {
		/*
		 *参考自 Java调用系统命令或可执行程序的方法介绍 http://developer.51cto.com/art/200909/149554.htm
		 *基本原理是，首先通过 Runtime.getRuntime() 返回与当前Java 应用程序相关的运行时对象，然后调用run.exec(cmd)  另启一个进程来执行命令（cmd为要执行的命令）
		 *其它说明：参考中还有如何把执行结果输出到控制台  用数组存储有参数的执行文件等。可进一步挖掘 
		 * */
		/*
		 * 如果直接写成 cmd ="path"则运行程序后没反应.批处理得由cmd.exe执行启动也是有道理的。
		 * 那如果在linux下执行就得用 /bin/sh /c path 了
		 * */
		String cmd="cmd.exe /c start z:/work/cur/cur-office/oracleBak-20160418/V2/oracleBackup.bat dataexchange dataexchange ORCL z:\\work\\dataBak\\oraclebaktest"; 
		Runtime run = Runtime.getRuntime();//返回与当前 Java 应用程序相关的运行时对象 
		try {
			Process p = run.exec(cmd);// 启动另一个进程来执行命令   
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
