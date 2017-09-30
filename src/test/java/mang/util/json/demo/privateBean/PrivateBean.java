package mang.util.json.demo.privateBean;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class PrivateBean {
	private int id;
	private String name;
}
