package mang.util.excel;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class ExcelImportTest {

	@Test
	public void test() {
		File file=new File("c:/excelDemo.xlsx");
		ExcelImport ei=new ExcelImport(file,UserInfo.class);
		ei.addForceStringFileld("code");
		List<UserInfo> lis=ei.doImport();
		
		for(UserInfo user:lis){
			System.out.println(user);
		}
		
	}

}
