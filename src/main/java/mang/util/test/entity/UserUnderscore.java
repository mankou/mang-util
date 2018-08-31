package mang.util.test.entity;

import java.io.Serializable;

public class UserUnderscore implements Serializable {
	private static final long	serialVersionUID	= -3004824622398622080L;
	private String				user_name;
	private String				id_number;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
}
