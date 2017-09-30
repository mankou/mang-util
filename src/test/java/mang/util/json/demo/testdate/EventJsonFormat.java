package mang.util.json.demo.testdate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EventJsonFormat {
	public String name;
	
	@JsonFormat(shape =JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss",timezone="GMT+8")
	public Date eventDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", eventDate=" + eventDate + "]";
	}
	
	
	
	
}
