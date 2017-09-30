package mang.util.json.demo.testdate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class testDate {
	
	
	@Test
	public void  testDate() throws IOException{
		Event event=new Event();
		event.setEventDate(new Date());
		ObjectMapper objectMapper=new ObjectMapper();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		objectMapper.setDateFormat(dateFormat);
		
		String str=objectMapper.writeValueAsString(event);
		System.out.println(str);
		
		Event event2=objectMapper.readValue(str, Event.class);
		System.out.println(event2);
	}
	
	@Test
	public void  testDateFormat() throws IOException{
		EventJsonFormat event=new EventJsonFormat();
		event.setEventDate(new Date());
		ObjectMapper objectMapper=new ObjectMapper();

		String str=objectMapper.writeValueAsString(event);
		System.out.println(str);
		
		EventJsonFormat event2=objectMapper.readValue(str, EventJsonFormat.class);
		System.out.println(event2);
	}
	
	
	
	
	
}
