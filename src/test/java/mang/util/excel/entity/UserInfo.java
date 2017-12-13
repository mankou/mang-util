package mang.util.excel.entity;

import java.util.Date;

public class UserInfo {
	private String code;
	private String name;
	private String city;
	
	private Date  birthday;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "UserInfo [code=" + code + ", name=" + name + ", city=" + city + "]";
	}
	
	
}
