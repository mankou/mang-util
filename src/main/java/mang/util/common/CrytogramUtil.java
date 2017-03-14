package mang.util.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class CrytogramUtil
{
  public static String encrypt(String paramString1, String paramString2)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString2);
      localMessageDigest.reset();
      byte[] arrayOfByte1 = paramString1.getBytes();
      byte[] arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
      BASE64Encoder localBASE64Encoder = new BASE64Encoder();
      return localBASE64Encoder.encode(arrayOfByte2);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return paramString1;
  }
}
