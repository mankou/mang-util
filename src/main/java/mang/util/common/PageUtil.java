package mang.util.common;

/**
 * 处理页数的工具类.
 * @author mang
 * @version 
 * create:2015-10-9 17:13:53 
 * modify:2015-10-9 17:18:26
 * 
 * */
public class PageUtil {
	
	/**
	 * 计算总页数.
	 * <p>如果总记录数842330 每页10000条 则总计85页 如果总记录数840000  则总计84页<br>
	 * 
	 * */
	public static int computeTotalPage(int totalCount,int pageSize){
		int totalPageNumber=totalCount/pageSize;
		if(totalCount%pageSize!=0){
			totalPageNumber++;
		}
		
		return totalPageNumber;
	}
	
	/**
	 * 计算某一页开始的记录.
	 * <p>如果每页显示2条记录  现在是第3页  则第3页的第1条记录是总的第5条记录
	 * 
	 * @param pageSize 每页显示多少条
	 * @param pageNumber 当前第几页 (页数从1开始 如第1页 第2页 没有第0页)
	 * @param isStartWith0 数据是从第0条开始 还是从第1条开始 true表示从0开始 false表示从1开始
	 * */
	public static int computeStart(int pageSize,int pageNumber,boolean isStartWith0){
		int start;
		if(isStartWith0){
			start=pageSize*(pageNumber-1);
		}else{
			start=pageSize*(pageNumber-1)+1;
		}
		return start;
	}
}
