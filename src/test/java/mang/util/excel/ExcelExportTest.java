package mang.util.excel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;

public class ExcelExportTest {

	@Test
	public void testExport(){
		try {
			String filePath="C:/students.xlsx";
			String[] headers=new String[]{"列1","列2","列3","时间列"};
			
			List userList=new ArrayList();
			UserInfo userInfo1=new UserInfo();
			userInfo1.setCode("001");
			userInfo1.setName("马宁");
			userInfo1.setCity("北京");
			userInfo1.setBirthday(new Date());
			
			UserInfo userInfo2=new UserInfo();
			userInfo2.setCode("002");
			userInfo2.setName("马宁");
			userInfo2.setCity("上海");
			userInfo2.setBirthday(new Date());
			
			UserInfo userInfo3=new UserInfo();
			userInfo3.setCode("003");
			userInfo3.setName("马宁");
			userInfo3.setCity("深圳");
			userInfo2.setBirthday(new Date());
			userList.add(userInfo1);
			userList.add(userInfo2);
			userList.add(userInfo3);
			
			ExcelExport excelExport=new ExcelExport();
			excelExport.setSheetTile("sheet测试");
			excelExport.setHeaders(headers);
			excelExport.setDataset(userList);
			excelExport.setFilePath(filePath);
			
			ExcelUtils.exportExcel(excelExport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
