package mang.util.txt.process;

import java.io.File;

public class RenameTxtFileProcessor extends AbstractTxtProcessor {
	
	@Override
	public void after() {
		System.out.println("重写");
		File targetFile=this.getTargetFile();
		int writeCount=this.getTxtWriter().getWriteCount();
		System.out.println(writeCount);
		String fileName="XX"+writeCount+".csv";
		String filePath=targetFile.getParent();
		File realFile=new File(filePath+"/"+fileName);
		targetFile.renameTo(realFile);
	}
}
