package mang.util.json;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomDateSerializerTest {
	@Test
	public void test() throws IOException{
		EventCustomDateSeralize event=new EventCustomDateSeralize();
		Date date=new Date();
		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeInMillis(0);
		
		Date date2=calendar.getTime();
		
		event.setEventDate(date2);
		
		ObjectMapper objectMapper=new ObjectMapper();
		String str=objectMapper.writeValueAsString(event);
		System.out.println(str);
		
		EventCustomDateSeralize event2=objectMapper.readValue(str, EventCustomDateSeralize.class);
		
		assertEquals(event.getEventDate().getTime(), event2.getEventDate().getTime());
		
	}
}
