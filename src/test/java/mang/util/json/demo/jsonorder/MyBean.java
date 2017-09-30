package mang.util.json.demo.jsonorder;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "id" })
public class MyBean {
	public int id;
	public String name;
	
	
	public MyBean(){
		this.id=id;
		this.name=name;
	}
	
	public MyBean(int id,String name){
		this.id=id;
		this.name=name;
	}
	

}
