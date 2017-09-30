package mang.util.demo;

import mang.util.common.CrytogramUtil;

public class CrytogramUtilTest {

	public static void main(String[] args) {
		String str1="123456";
		String result=CrytogramUtil.encrypt(str1, "MD5");
		System.out.println(result);
	}

}
