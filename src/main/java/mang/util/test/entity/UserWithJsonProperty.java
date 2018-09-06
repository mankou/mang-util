package mang.util.test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserWithJsonProperty {

	@JsonProperty(value = "USER_NAME")  
	private String userName;
	
	@JsonProperty(value = "USER_CODE")  
	private String userCode;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
