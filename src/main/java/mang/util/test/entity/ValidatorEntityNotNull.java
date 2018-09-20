package mang.util.test.entity;


import javax.validation.constraints.NotNull;


public class ValidatorEntityNotNull {
	
	
	/*
	 * 我发现没有javax中有NotNull,但在hibernate中没有,好奇怪
	 * */
	
	@NotNull(message="不能为null")  
	private String userCode;
	
	private String userName;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
