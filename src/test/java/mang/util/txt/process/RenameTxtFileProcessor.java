package mang.util.txt.process;

import java.io.File;

public class RenameTxtFileProcessor extends AbstractTxtProcessor {
	
	@Override
	public void after() {
		
		String userData=(String) this.getUserData("prefix");
		System.out.println(userData);
		

		File targetFile=this.getTargetFile();
		int writeCount=this.getTxtWriter().getWriteCount();
		
		System.out.println(writeCount);
		String fileName="XX"+userData+writeCount+".csv";
		String filePath=targetFile.getParent();
		File realFile=new File(filePath+"/"+fileName);
		targetFile.renameTo(realFile);
	}
}
