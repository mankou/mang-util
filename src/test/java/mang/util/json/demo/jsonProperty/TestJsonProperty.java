package mang.util.json.demo.jsonProperty;

import org.junit.Test;

import mang.util.json.JsonUtil;

public class TestJsonProperty {
	
	/**
	 * 测试java类首字母大写，希望生成的json的属性也是首字母大写
	 * 
	 * 需要给属性设置成   @JsonProperty 标签
	 * 给类的get方法设置@JsonIgnore 标签 （如果不设置 生成的json中会有2个属性 一个是首字母大写的 另一个是首字母小写的）
	 * 
	 * */
	@Test
	public void testJsonProperty(){
		Bean1 bean1=new Bean1();
		String str=JsonUtil.obj2String(bean1);
		System.out.println(str);
	}
}
