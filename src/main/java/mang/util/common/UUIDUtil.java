package mang.util.common;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉“-”符号
		return uuid.replaceAll("-", "");
	}
}
