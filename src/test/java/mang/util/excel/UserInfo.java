package mang.util.excel;


public class UserInfo {
	private String code;
	private String name;
	private String city;
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
	@Override
	public String toString() {
		return "UserInfo [code=" + code + ", name=" + name + ", city=" + city + "]";
	}
	
	
}
