package mang.util.excel;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mang.util.excel.entity.UserInfo;

public class ExcelExportTest {
	private String filePath;
	private String[] headers;
	private List userList;
	
	@Before
	public void prepareData(){
		filePath = "C:/students.xlsx";
		headers = new String[] { "列1", "列2", "列3", "时间列" };
		userList = new ArrayList();
		UserInfo userInfo1 = new UserInfo();
		userInfo1.setCode("001");
		userInfo1.setName("马宁");
		userInfo1.setCity("北京");
		userInfo1.setBirthday(new Date());

		UserInfo userInfo2 = new UserInfo();
		userInfo2.setCode("002");
		userInfo2.setName("马宁");
		userInfo2.setCity("上海");
		userInfo2.setBirthday(new Date());

		UserInfo userInfo3 = new UserInfo();
		userInfo3.setCode("003");
		userInfo3.setName("马宁");
		userInfo3.setCity("深圳");
		userInfo2.setBirthday(new Date());
		userList.add(userInfo1);
		userList.add(userInfo2);
		userList.add(userInfo3);
	}
	

	@Test
	public void testExport() {
		ExcelExport excelExport = new ExcelExport();
		excelExport.setSheetTile("sheet测试");
		excelExport.setHeaders(headers);
		excelExport.setDataset(userList);
		excelExport.setFilePath(filePath);
		ExcelUtils.exportExcel(excelExport);
		
		File file=new File(filePath);
		if(file.exists()){
			assertTrue(true);
		}else{
			assertTrue(false);
		}
		
	}

}
