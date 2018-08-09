package com.ancao.treasures.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密类
 * 
 * @author Administrator
 * @version [v1.0, 2012-2-27]
 * @see
 * @since
 */
public class MD5Util
{
    /**
     * 获取加密字符串
     * 
     * @param value
     * @return
     * @since <IVersion>
     */
    public static String getEncryptStr(String value)
    {
        final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try
        {
            final byte[] strTemp = value.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            final byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j << 1];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
    }
}
