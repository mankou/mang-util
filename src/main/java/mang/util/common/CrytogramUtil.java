package mang.util.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类
 * 
 * @author mang
 */
public class CrytogramUtil {
	
	/**
	 * 将字符串换某种算法加密后用base64转码.
	 * @param str 待加密字符串
	 * @param encryptionAlgorith 加密算法 如MD5
	 * 
	 * @return String 加密后字符串
	 * */
	public static String encrypt(String str, String encryptionAlgorith) {
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance(encryptionAlgorith);
			localMessageDigest.reset();
			byte[] arrayOfByte1 = str.getBytes();
			byte[] arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
			BASE64Encoder localBASE64Encoder = new BASE64Encoder();
			return localBASE64Encoder.encode(arrayOfByte2);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			localNoSuchAlgorithmException.printStackTrace();
		}
		return str;
	}
}
