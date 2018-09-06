package mang.util.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import mang.util.json.LowerCamelToUpperUnderscoreStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;


public class MapBeanUtil {
    private static final Logger log = LoggerFactory.getLogger(MapBeanUtil.class);
    
    /**
     * 将List<Bean> 转成List<Map>, 而且可以自定义命名策略.
     * 实际上这里借助了jackson
     * */
    public static List<Map> listBean2ListMap(List lis,PropertyNamingStrategy namingStrategy){
        try{
            ObjectMapper mapper = new ObjectMapper();
            if(namingStrategy!=null){
                mapper.setPropertyNamingStrategy(new LowerCamelToUpperUnderscoreStrategy());
            }
            String str=mapper.writeValueAsString(lis);
            List<Map> resultList=mapper.readValue(str, List.class);
            return resultList;
        }catch (Exception e){
            log.error("convert to map error",e);
        }
        return null;
    }
}
