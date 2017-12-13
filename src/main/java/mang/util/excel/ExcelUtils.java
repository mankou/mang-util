package mang.util.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import mang.util.common.FieldUtil;
import mang.util.common.FileUtil;

/**
 * excel导入导出工具类
 */
public class ExcelUtils {

	/**
	 * 生成excle的工具类.
	 * @param excelExport 生成excel时需要的信息 如数据，excel文件路径等
	 * */
	public static void exportExcel(ExcelExport excelExport) {
		String title=excelExport.getSheetTile();
		String[] headers=excelExport.getHeaders();
		Collection dataset=excelExport.getDataset();
		String dateFormatter=excelExport.getDateFormatter();
		String filePath=excelExport.getFilePath();
		
		String postfix = FileUtil.getFileType(filePath);
		
		// 声明一个工作薄
		Workbook workbook =null;
		
		if ("xls".equals(postfix)) {
			workbook=new HSSFWorkbook();
		} else if ("xlsx".equals(postfix)) {
			workbook=new XSSFWorkbook();
		}	
			
		// 生成一个表格
		Sheet sheet = workbook.createSheet(title);

		// 先不处理样式
		// 设置表格默认列宽度为15个字节
		// sheet.setDefaultColumnWidth((short) 15);

		// // 生成一个样式
		// HSSFCellStyle style = workbook.createCellStyle();
		// // 设置这些样式
		// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// // 生成一个字体
		// HSSFFont font = workbook.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		// font.setFontHeightInPoints((short) 12);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// // 把字体应用到当前的样式
		// style.setFont(font);
		// // 生成并设置另一个样式
		// HSSFCellStyle style2 = workbook.createCellStyle();
		// style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		// style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// // 生成另一个字体
		// HSSFFont font2 = workbook.createFont();
		// font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// // 把字体应用到当前的样式
		// style2.setFont(font2);
		//
		// // 声明一个画图的顶级管理器
		// HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// // 定义注释的大小和位置,详见文档
		// HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
		// 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// // 设置注释内容
		// comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		// comment.setAuthor("leno");

		// 产生表格标题行
		if (headers != null && headers.length > 0) {
			Row row = sheet.createRow(0);
			for (int i = 0; i < headers.length; i++) {
				Cell cell = row.createCell(i);
				// cell.setCellStyle(style);
//				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(headers[i]);
			}
		}

		Set<Integer> dateColumn = new HashSet<Integer>();
		// 遍历集合数据，产生数据行
		Iterator it = dataset.iterator();
		int rowIndex = 0;
		while (it.hasNext()) {
			rowIndex++;
			Row row = sheet.createRow(rowIndex);
			Object t = (Object) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (int columnIndex = 0; columnIndex < fields.length; columnIndex++) {
				Cell cell = row.createCell(columnIndex);
				// cell.setCellStyle(style2);
				Field field = fields[columnIndex];
				String fieldName = field.getName();
				Object value = FieldUtil.getFieldValue(t, fieldName);
				if (value == null) {
					continue;
				}

				if (value instanceof Boolean) {
					cell.setCellValue((Boolean) value);
				} else if (value instanceof Calendar) {
					cell.setCellValue((Calendar) value);
				} else if (value instanceof Date) {
					cell.setCellValue((Date) value);
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(dateFormatter));
					cell.setCellStyle(cellStyle);
					dateColumn.add(columnIndex);
				} else if (value instanceof Double) {
					cell.setCellValue((Double) value);
				} else {
					cell.setCellValue(value.toString());
				}
			}
		}

		// 时间列宽度加长
		for (int index : dateColumn) {
			sheet.autoSizeColumn(index);
			int columnWidth=sheet.getColumnWidth(index);
			sheet.setColumnWidth(index, columnWidth+256);
		}

		// 生成文件
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成excel的工具类
	 * @param sheetTile
	 *            excel sheet的名称
	 * @param headers
	 *            excel表头名
	 * @param dataset
	 *            需要显示的数据集合
	 * @param filePath
	 *            文件路径
	 */
	@SuppressWarnings("unchecked")
	public static <T> void exportExcel(String sheetTile, String[] headers, Collection<T> dataset, String filePath) {
		ExcelExport excelExport=new ExcelExport();
		excelExport.setSheetTile(sheetTile);
		excelExport.setHeaders(headers);
		excelExport.setDataset(dataset);
		excelExport.setFilePath(filePath);
		ExcelUtils.exportExcel(excelExport);
	}

	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param ei
	 *            excel导入辅助类 用于设置一些参数
	 * @return List
	 * @throws Exception
	 *             Exception
	 */
	public static List importExcel(ExcelImport ei) throws Exception {
		InputStream is = ei.getInputStream();
		String fileName = ei.getFileName();
		Workbook workbook = null;

		String postfix = FileUtil.getFileType(fileName);
		Assert.notNull(postfix);
		FileType fileType = FileType.Excel03;
		if ("xls".equals(postfix)) {
			fileType = FileType.Excel03;
		} else if ("xlsx".equals(postfix)) {
			fileType = FileType.Excel07;
		}

		if (FileType.Excel03.equals(fileType)) {
			workbook = new HSSFWorkbook(is);
		} else if (FileType.Excel07.equals(fileType)) {
			workbook = new XSSFWorkbook(is);
		}

		return parseExcel(workbook, ei);
	}

	/**
	 * 解析一个workbook
	 */
	private static List parseExcel(Workbook workbook, ExcelImport ei) throws Exception {
		List list = new ArrayList();
		int sheetNum = ei.getSheetNum();

		int numberOfSheet = workbook.getNumberOfSheets();
		if (sheetNum == -1) {
			for (int i = 0; i < numberOfSheet; i++) {
				System.out.println("===============正在解析sheet" + i + "===================");
				Sheet sheet = workbook.getSheetAt(sheetNum);
				List sheetList = parseSheet(sheet, ei);
				list.addAll(sheetList);
			}
		} else if (sheetNum < numberOfSheet) {
			System.out.println("===============正在解析sheet" + sheetNum + "===================");
			Sheet sheet = workbook.getSheetAt(sheetNum);
			list = parseSheet(sheet, ei);
		}
		return list;
	}

	/**
	 * 解析一个Sheet
	 */
	private static List parseSheet(Sheet sheet, ExcelImport ei) throws Exception {
		List list = new ArrayList();

		boolean isSkipFirst = ei.isSkipFirstRow();
		Class objClass = ei.getBeanClass();
		Set<String> stringFieldName = ei.getForceStringFieldName();

		Workbook workbook = sheet.getWorkbook();

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		int minRowIx = sheet.getFirstRowNum();
		int maxRowIx = sheet.getLastRowNum();
		if (isSkipFirst) {
			minRowIx = minRowIx + 1;
		}
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			Row row = sheet.getRow(rowIx);
			Object beanObject = objClass.newInstance();
			Field[] fields = objClass.getDeclaredFields();
			int minColIx = 0;
			int maxColIx = fields.length - 1;
			if (row == null) {
				System.out.println("第" + rowIx + "行为空跳过");
				continue;
			}
			System.out.println("====正在导入第" + rowIx + "行====");
			for (int colIx = minColIx; colIx <= maxColIx; colIx++) {
				System.out.println("====正在导入第" + colIx + "列====");
				Field field = fields[colIx];
				field.setAccessible(true);
				Cell cell = row.getCell(new Integer(colIx));
				if (cell == null) {
					System.out.println("第" + colIx + "列为空跳过");
					continue;
				}

				// 针对有些列 强转类型为string
				if (stringFieldName.contains(field.getName())) {
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
				CellValue cellValue = evaluator.evaluate(cell);
				System.out.println(cellValue);
				// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
				// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
				if (cellValue == null) {
					System.out.println("第" + colIx + "列,值为空跳过");
					continue;
				}

				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					Boolean booleanValue = cellValue.getBooleanValue();
					if (FieldUtil.isBooleanClass(field.getType())) {
						field.setBoolean(beanObject, booleanValue.booleanValue());
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					// 这里的日期类型会被转换为数字类型，需要判别后区分处理
					if (DateUtil.isCellDateFormatted(cell)) {
						Date dateValue = cell.getDateCellValue();
						field.set(beanObject, dateValue);
					} else {
						Double doubleValue = cellValue.getNumberValue();
						Object objValue = FieldUtil.parseNumber(doubleValue.toString(), field.getType());
						field.set(beanObject, objValue);
					}
					break;
				case Cell.CELL_TYPE_STRING:
					String stringValue = cell.getStringCellValue();
					if (("TRUE".equalsIgnoreCase(stringValue) || "FALSE".equalsIgnoreCase(stringValue))
							&& FieldUtil.isBooleanClass(field.getType())) {
						field.setBoolean(beanObject, new Boolean(stringValue));
					} else if (FieldUtil.isNumberClass(field.getType())) {
						Object objValue = FieldUtil.parseNumber(stringValue, field.getType());
						field.set(beanObject, objValue);
					} else {
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
