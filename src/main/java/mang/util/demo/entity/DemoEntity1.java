package mang.util.demo.entity;


import mang.util.common.EntityUtil;

public class DemoEntity1 {

	public static void main(String[] args) {
		TestEntity tn=new TestEntity();
		EntityUtil.setEntityValue(tn, "code", "1code");
		EntityUtil.setEntityValue(tn, "name", "1name");
		EntityUtil.setEntityValue(tn, "errorcode", "errorcode");
		System.out.println("code:"+tn.getCode()+" name:"+tn.getName());

	}

}
