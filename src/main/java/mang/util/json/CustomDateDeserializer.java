package mang.util.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Object> {
	private String default_dateFormater = "yyyy-MM-dd hh:mm:ss";
	private String default_timeZone = "GMT+8";

	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String date = jp.getText();
		SimpleDateFormat formater = new SimpleDateFormat(default_dateFormater);
		formater.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		try {
			return formater.parse(date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getDefault_dateFormater() {
		return default_dateFormater;
	}

	public void setDefault_dateFormater(String default_dateFormater) {
		this.default_dateFormater = default_dateFormater;
	}

	public String getDefault_timeZone() {
		return default_timeZone;
	}

	public void setDefault_timeZone(String default_timeZone) {
		this.default_timeZone = default_timeZone;
	}
	
	

}
