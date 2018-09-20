package mang.util.test.entity;


import org.hibernate.validator.constraints.NotEmpty;


public class ValidatorEntityNotEmpty {
	
	
	/*
	 * 我发现使用javax中的NotEmpty校验没效果,但是用hibernate的有效,所以这里用Hibernate的
	 * */
	
	@NotEmpty(message="不能为empty")  
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
