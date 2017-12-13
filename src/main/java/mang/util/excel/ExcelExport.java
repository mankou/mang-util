package mang.util.excel;

import java.util.Collection;

/**
 * excel导出的辅助类 用于设置导出时的一些参数
 */
public class ExcelExport {
	/**
	 * sheet 名
	 * */
	private String sheetTile = "sheet1";

	/**
	 * 表头
	 * */
	private String[] headers;

	/**
	 * 数据
	 * */
	private Collection dataset;

	/**
	 * 文件路径
	 * */
	private String filePath;
	
	
	/**
	 * 时间格式
	 * 针对单元格是时间类型时 设置时间格式
	 * */
	private String dateFormatter = "yyyy-m-d h:mm:ss";


	public String getDateFormatter() {
		return dateFormatter;
	}

	public void setDateFormatter(String dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	public String getSheetTile() {
		return sheetTile;
	}

	public void setSheetTile(String sheetTile) {
		this.sheetTile = sheetTile;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public Collection getDataset() {
		return dataset;
	}

	public void setDataset(Collection dataset) {
		this.dataset = dataset;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
