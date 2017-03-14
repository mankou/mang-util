package mang.util.excel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import mang.util.common.FieldUtil;
import mang.util.common.FileUtil;


public class ExcelUtils {

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 *
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public static <T> void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out,
			String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					// if (value instanceof Integer) {
					// int intValue = (Integer) value;
					// cell.setCellValue(intValue);
					// } else if (value instanceof Float) {
					// float fValue = (Float) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(fValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Double) {
					// double dValue = (Double) value;
					// textValue = new HSSFRichTextString(
					// String.valueOf(dValue));
					// cell.setCellValue(textValue);
					// } else if (value instanceof Long) {
					// long longValue = (Long) value;
					// cell.setCellValue(longValue);
					// }
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else {
						// 其它数据类型都当作字符串简单处理
						if(value == null){
							value = "";
						}
						textValue = value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}

		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param fileType
	 * @param sheetNum 默认第一个sheet编号为0
	 * @return
	 * @throws Exception 
	 */
	public static <T> List<T> importExcel(InputStream is, String fileName, int sheetNum, boolean isSkipFirst,Class<T> ojbClass) throws Exception {

		Workbook workbook = null;
		
		String postfix=FileUtil.getFileType(fileName);
		
		Assert.notNull(postfix);
		FileType fileType=FileType.Excel03;
		if("xls".equals(postfix)){
			fileType=FileType.Excel03;
		}else if("xlsx".equals(postfix)){
			fileType=FileType.Excel07;
		}

		if (FileType.Excel03.equals(fileType)) {
			workbook = new HSSFWorkbook(is);
		} else if (FileType.Excel07.equals(fileType)) {
			workbook = new XSSFWorkbook(is);
		}

		return parseExcel(workbook, sheetNum,isSkipFirst,ojbClass);
	}

	private static <T> List<T> parseExcel(Workbook workbook, int sheetNum , boolean isSkipFirst,Class<T> ojbClass) throws Exception{

		Sheet sheet = workbook.getSheetAt(sheetNum);

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		List<T> list = new ArrayList<T>();

		int minRowIx = sheet.getFirstRowNum();
		int maxRowIx = sheet.getLastRowNum();
		if(isSkipFirst){
			minRowIx=minRowIx+1;
		}
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			Row row = sheet.getRow(rowIx);
			T beanObject=ojbClass.newInstance();
			Field[] fields = ojbClass.getDeclaredFields();
			int minColIx = 0;
			int maxColIx = fields.length-1;
			for (int colIx = minColIx; colIx <= maxColIx; colIx++) {
				
				Field field = fields[colIx];
				field.setAccessible(true);
				
//				String fieldName = field.getName();
//				String getMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//				Method getMethod = ojbClass.getMethod(getMethodName, new Class[] {field.getType()});
			
				Cell cell = row.getCell(new Integer(colIx));
				CellValue cellValue = evaluator.evaluate(cell);
				if (cellValue == null) {
					continue;
				}
				// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
				// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
				
				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					Boolean booleanValue=cellValue.getBooleanValue();
					if(FieldUtil.isBooleanClass(field.getType())){
						field.setBoolean(beanObject, booleanValue.booleanValue());
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					// 这里的日期类型会被转换为数字类型，需要判别后区分处理
					if (DateUtil.isCellDateFormatted(cell)) {
						Date dateValue=cell.getDateCellValue();
						field.set(beanObject, dateValue);
					} else {
						Double doubleValue=cellValue.getNumberValue();
//						if(field.getType()==int.class || field.getType()==Integer.class){
//							field.setInt(beanObject, doubleValue.intValue());
//						}else if(field.getType()==long.class || field.getType()==Long.class){
//							field.setLong(beanObject, doubleValue.longValue());
//						}
						 Object objValue=FieldUtil.parseNumber(doubleValue.toString(), field.getType());
						field.set(beanObject,objValue);
					}
					break;
				case Cell.CELL_TYPE_STRING:
					String stringValue=cell.getStringCellValue();
					 if(("TRUE".equalsIgnoreCase(stringValue) || "FALSE".equalsIgnoreCase(stringValue) ) && FieldUtil.isBooleanClass(field.getType())){
						 field.setBoolean(beanObject, new Boolean(stringValue));
					 }else if(FieldUtil.isNumberClass(field.getType())){
						 Object objValue=FieldUtil.parseNumber(stringValue, field.getType());
						 field.set(beanObject,objValue);
					 }else{
						 field.set(beanObject, stringValue);
					 }
					break;
				case Cell.CELL_TYPE_FORMULA:
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR:
					break;
				default:
					break;
				}
			}
			list.add(beanObject);
		}
		return list;
	}

}
