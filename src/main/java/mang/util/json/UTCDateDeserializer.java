package mang.util.json;

import java.io.IOException;  
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
import org.apache.log4j.Logger;  
  
import com.fasterxml.jackson.core.JsonParser;  
import com.fasterxml.jackson.core.JsonProcessingException;  
import com.fasterxml.jackson.databind.DeserializationContext;  
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;  
  
public class UTCDateDeserializer extends DateDeserializer {  
    private Logger logger = Logger.getLogger(getClass());  
    private static final long   serialVersionUID    = -6218693745160760598L;  
  
    @Override  
    protected Date _parseDate(JsonParser jp, DeserializationContext ctxt)  
            throws IOException, JsonProcessingException {  
        Date _parseDate = null;  
        try {  
            _parseDate = super._parseDate(jp, ctxt);  
        } catch (Exception ex) {  
            logger.trace(ex.getMessage(), ex.getCause());  
            String dateStr = jp.getText().trim();  
              
            DateFormat sourceDf = ctxt.getConfig().getDateFormat();  
            System.out.println(sourceDf);
              
          
        }  
        return _parseDate;  
    }  
}  
