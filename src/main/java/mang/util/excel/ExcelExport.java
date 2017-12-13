package mang.util.excel;

import java.util.Collection;

public class ExcelExport {
	private String dateFormatter="yyyy-m-d h:mm:ss";
	
	private String sheetTile="sheet1";
	
	private String[] headers;
	
	private Collection dataset;
	
	private String filePath;
	
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
