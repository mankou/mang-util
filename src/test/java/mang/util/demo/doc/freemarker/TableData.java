package mang.util.demo.doc.freemarker;

/**
 * 用于辅助演示用freemarker生成word的辅助类.
 * 用于辅助展示用freemarker生成带表格的word的辅助类
 * */
public class TableData {
	private String name;
	private String comment;
	
	public TableData(String name,String comment){
		this.name=name;
		this.comment=comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
