package mang.util.json.demo.testdate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event {
	public String name;
	
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
