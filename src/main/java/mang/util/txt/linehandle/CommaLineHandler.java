package mang.util.txt.linehandle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  将原来由双竖线||分割的字符串 处理成逗号分割,而且如果数据项中有逗号则将该数据项用双引号括起来
 *  原始数据  1||1P,MC||M,33||2013,1015||||N
 *  处理结果 1,"1P,MC","M,33","2013,1015",,N 
 * */
public class CommaLineHandler implements LineHandler {
	
	private String regularSpliter="\\|\\|";
	
	private String realSpliter="||";
	
	private String writerSpliter=",";
	
	private String inSideStr=",";
	
	private String outSideStr="\"";
	
	
	public void CommaLineHandler(){
		
	}
	

	@Override
	public String processLine(String line) {
		//用apache 的StringUtils 会把空值过滤掉 我不想过滤 所以用如下方法
//		String[] values=StringUtils.split(line, spliter);
		List<String> strList=new ArrayList<String>();
		//试验了 用line.split("||") 不能分割  但用line.split("\\|\\|") 可以，估计||是特殊字符
		
		//fix ||结尾问题
		// 如果8||X3QK1||M0JP||6CFX||||||  则执行split()后,则只能解析出前面的几列,最后面的几列没有
		//为了解决这个问题我特意加一列,split()后再把最后一列去掉
//		String[] values=line.split(spliter);
		String line2=line+realSpliter+" ";
		String[] valuesTmp=line2.split(regularSpliter);
		String[] values = Arrays.copyOf(valuesTmp, valuesTmp.length - 1);
		
		for(String value:values){
			if(value.indexOf(inSideStr)>-1){
				value=outSideStr+value+outSideStr;
			}
			strList.add(value);
		}
		StringBuffer sb = new StringBuffer();
		int size=strList.size();
		for(int i = 0; i < size; i++){
			sb.append(strList.get(i));
			//最后一列不加分隔符
			if(i!=(size-1)){
				sb.append(writerSpliter);
			}
		}
		String result = sb.toString();
		return result;
	}


	public String getRegularSpliter() {
		return regularSpliter;
	}

	public void setRegularSpliter(String regularSpliter) {
		this.regularSpliter = regularSpliter;
	}

	public String getRealSpliter() {
		return realSpliter;
	}

	public void setRealSpliter(String realSpliter) {
		this.realSpliter = realSpliter;
	}

	public String getWriterSpliter() {
		return writerSpliter;
	}

	public void setWriterSpliter(String writerSpliter) {
		this.writerSpliter = writerSpliter;
	}

	public String getInSideStr() {
		return inSideStr;
	}

	public void setInSideStr(String inSideStr) {
		this.inSideStr = inSideStr;
	}

	public String getOutSideStr() {
		return outSideStr;
	}

	public void setOutSideStr(String outSideStr) {
		this.outSideStr = outSideStr;
	}



}
