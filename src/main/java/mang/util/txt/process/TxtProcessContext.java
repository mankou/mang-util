package mang.util.txt.process;

import java.util.HashMap;
import java.util.Map;

public class TxtProcessContext {
	private int currentLineNum=0;
	
	private Map<String,Object> userData=new HashMap<String, Object>();
	
	
	public void addLine(){
		this.currentLineNum++;
	}
	

	public int getCurrentLineNum() {
		return currentLineNum;
	}

	public void setCurrentLineNum(int currentLineNum) {
		this.currentLineNum = currentLineNum;
	}

	
	public void putUserData(String key,Object value){
		this.userData.put(key, value);
	}
	
	public Object getUserData(String key){
		return this.userData.get(key);
	}
	

	public Map<String, Object> getUserDataMap() {
		return userData;
	}

	public void setUserDataMap(Map<String, Object> userData) {
		this.userData = userData;
	}
	
	
	
	
}
