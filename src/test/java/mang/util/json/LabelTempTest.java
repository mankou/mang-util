package mang.util.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mang.util.test.entity.LabelDTO;
import mang.util.test.entity.LabelValueDTO;

public class LabelTempTest {
	
	@Test
	public void generateJson(){
		LabelDTO labelDTO1=new LabelDTO();
		labelDTO1.setLabelId("1001");
		labelDTO1.setLabelName("套餐分类");
		labelDTO1.setLabelType("type1");
		
		LabelValueDTO labelValuedto1=new LabelValueDTO();
		labelValuedto1.setLabelValueId("2001");
		labelValuedto1.setLabelValueName("全国套餐");
		labelValuedto1.setProvinceCode("ZZZZ");
		labelValuedto1.setEparchyCode("ZZZZ");
		
		LabelValueDTO labelValuedto2=new LabelValueDTO();
		labelValuedto2.setLabelValueId("2002");
		labelValuedto2.setLabelValueName("校园套餐");
		labelValuedto2.setProvinceCode("ZZZZ");
		labelValuedto2.setEparchyCode("ZZZZ");
		labelDTO1.addLabelValue(labelValuedto1);
		labelDTO1.addLabelValue(labelValuedto2);
		
		LabelDTO labelDTO2=new LabelDTO();
		labelDTO2.setLabelId("1001");
		labelDTO2.setLabelName("套餐分类");
		labelDTO2.setLabelType("type1");
		
		LabelValueDTO labelValuedto3=new LabelValueDTO();
		labelValuedto3.setLabelValueId("2003");
		labelValuedto3.setLabelValueName("本地套餐");
		labelValuedto3.setProvinceCode("ZZZZ");
		labelValuedto3.setEparchyCode("ZZZZ");
		labelDTO2.addLabelValue(labelValuedto3);
		
		List<LabelDTO> labelList=new ArrayList<LabelDTO>();
		labelList.add(labelDTO1);
		labelList.add(labelDTO1);
		
		String jsonStr=JsonUtil.obj2String(labelList);
		System.out.println(jsonStr);
	}
	
	@Test
	public void generateJson2() throws Exception{
		LabelDTO labelDTO1=new LabelDTO();
		labelDTO1.setLabelId("1001");
		labelDTO1.setLabelName("套餐分类");
		labelDTO1.setLabelType("type1");
		
		LabelValueDTO labelValuedto1=new LabelValueDTO();
		labelValuedto1.setLabelValueId("2001");
		labelValuedto1.setLabelValueName("全国套餐");
		labelValuedto1.setProvinceCode("ZZZZ");
		labelValuedto1.setEparchyCode("ZZZZ");
		
		LabelValueDTO labelValuedto2=new LabelValueDTO();
		labelValuedto2.setLabelValueId("2002");
		labelValuedto2.setLabelValueName("校园套餐");
		labelValuedto2.setProvinceCode("ZZZZ");
		labelValuedto2.setEparchyCode("ZZZZ");
		labelDTO1.addLabelValue(labelValuedto1);
		labelDTO1.addLabelValue(labelValuedto2);
		
		LabelDTO labelDTO2=new LabelDTO();
		labelDTO2.setLabelId("1001");
		labelDTO2.setLabelName("套餐分类");
		labelDTO2.setLabelType("type1");
		
		LabelValueDTO labelValuedto3=new LabelValueDTO();
		labelValuedto3.setLabelValueId("2003");
		labelValuedto3.setLabelValueName("本地套餐");
		labelValuedto3.setProvinceCode("ZZZZ");
		labelValuedto3.setEparchyCode("ZZZZ");
		labelDTO2.addLabelValue(labelValuedto3);
		
		List<LabelDTO> labelList=new ArrayList<LabelDTO>();
		labelList.add(labelDTO1);
		labelList.add(labelDTO1);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(new LowerCamelToUpperUnderscoreStrategy());
		String str=mapper.writeValueAsString(labelList);
		System.out.println(str);
	}

}
