package mang.util.txt.process;


import org.junit.Test;
import mang.util.txt.linefilter.BlankFilter;
import mang.util.txt.linefilter.EchoFilter;
import mang.util.txt.linehandle.CommaLineHandler;

public class SimpleTxtFilterProcessTest {
	
	private String sourceFilePath="c:/test/data/test1.txt";
	private String targetFilePath="c:/test/data/test2.csv";
	

	@Test
	public void test1(){	
		AbstractTxtProcessor txtFileProcess=new SimpleTxtFileProcessor();
		txtFileProcess.getLineFilterProcessor().addBeforeFilter(new BlankFilter());
		txtFileProcess.getLineHandleProcessor().addHandler(new CommaLineHandler());
		txtFileProcess.processSingleFile(sourceFilePath, targetFilePath);
		int count=txtFileProcess.getLineHandleProcessor().getProcessCount();
		System.out.println("处理行数:"+count);
	}
	
	@Test
	public void testComma(){
		String str="1,2,3,,,";
		String[] array=str.split(",");
		int length=array.length;
		System.out.println(length);
		System.out.println(array);
	}
	
	
	/*
	 * 这里多了2句 用于演示设置linehandle和linefilter
	 * */
	@Test
	public void test2(){
		AbstractTxtProcessor txtFileProcess=new AbstractTxtProcessor();
		txtFileProcess.setLineHandleProcessor(new SimpleLineHandleProcessor());
		txtFileProcess.setLineFilterProcessor(new AllTxtLineFilterProcessor());
		txtFileProcess.getLineFilterProcessor().addBeforeFilter(new BlankFilter());
		txtFileProcess.getLineHandleProcessor().addHandler(new CommaLineHandler());
		txtFileProcess.processSingleFile(sourceFilePath, targetFilePath);
		int count=txtFileProcess.getLineHandleProcessor().getProcessCount();
		System.out.println("处理行数:"+count);
	}
	
	/*
	 * 演示在处理完txt后重命名的代码片断
	 * */
	@Test
	public void testAfterRename(){
		//演示转换完后重命名文件
		AbstractTxtProcessor txtFileProcess=new RenameTxtFileProcessor();
		txtFileProcess.setUserData("prefix", "testPrefix");
		
		//这里可以自定义行处理类 和 filter处理类 如果不指定就会用默认的
		txtFileProcess.setLineHandleProcessor(new SimpleLineHandleProcessor());
		txtFileProcess.setLineFilterProcessor(new AllTxtLineFilterProcessor());
		
		//添加前置过滤器
		txtFileProcess.getLineFilterProcessor().addBeforeFilter(new BlankFilter());
		
		//添加行处理器
		txtFileProcess.getLineHandleProcessor().addHandler(new CommaLineHandler());
		
		//运行
		txtFileProcess.processSingleFile(sourceFilePath, targetFilePath);
	}
	
	/*
	 * 测试在lineFilter中获取当前实际行
	 * 因为你处理filter的策略不同 有的是全部通过才算通过 有的是有一个通过就算通过 所以导致有的filter得不到运行 所以自己统计行号肯定是不准的,所以通过context上下文的方式传进来
	 * */
	@Test
	public void testFilterContext(){
		AbstractTxtProcessor txtFileProcess=new SimpleTxtFileProcessor();
		txtFileProcess.getLineFilterProcessor().addBeforeFilter(new BlankFilter());
		txtFileProcess.getLineFilterProcessor().addBeforeFilter(new EchoFilter());
		txtFileProcess.getLineHandleProcessor().addHandler(new CommaLineHandler());
		txtFileProcess.processSingleFile(sourceFilePath, targetFilePath);
	}
}
