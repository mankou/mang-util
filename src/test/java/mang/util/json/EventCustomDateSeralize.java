package mang.util.json;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class EventCustomDateSeralize {
	public String name;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@JsonDeserialize(using=CustomDateDeserializer.class)
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
