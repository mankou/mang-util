package mang.util.common;

public class StrArrayUtilTest {

	public static void main(String[] args) {
		String[] array={"60","60万"};
		String str="60万平方米";
		
		boolean flag=StrArrayUtil.strIndexArray(str,array);
		System.out.println(flag);
		
		
		boolean flag2=StrArrayUtil.strIndexArray("70",array);
		System.out.println(flag2);

	}

}
