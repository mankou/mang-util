package mang.util.test.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String userName;
	private String userCode;
	
	private List<Address> address=new ArrayList<Address>();
	
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
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	public void addAddress(Address a){
		this.address.add(a);
	}
	
	
	
	

}
