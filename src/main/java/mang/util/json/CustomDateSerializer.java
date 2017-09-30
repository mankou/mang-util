package mang.util.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Object> {
	private String default_dateFormat="yyyy-MM-dd HH:mm:ss";
	private String default_timeZone="GMT+8";
	
	
	@Override
	public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone(default_timeZone));
		String formattedDate = formatter.format(value);
		jgen.writeString(formattedDate);
	}

	public String getDefault_dateFormat() {
		return default_dateFormat;
	}

	public void setDefault_dateFormat(String default_dateFormat) {
		this.default_dateFormat = default_dateFormat;
	}

	public String getDefault_timeZone() {
		return default_timeZone;
	}

	public void setDefault_timeZone(String default_timeZone) {
		this.default_timeZone = default_timeZone;
	}
}
