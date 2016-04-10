package com.test.common;

import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import java.io.UnsupportedEncodingException;  
  
public class Md5 {  
  
    public static String getMd5Str(String str)  {  
        MessageDigest messageDigest = null;  
  
        try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
  
        messageDigest.reset();  
  
        try {
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {  
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(  
                        Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }  
  
}  