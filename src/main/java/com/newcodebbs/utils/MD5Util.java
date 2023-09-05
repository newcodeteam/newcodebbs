package com.newcodebbs.utils;

import org.springframework.util.DigestUtils;

/**
 * md5加密
 */
public class MD5Util {
    static String salt= "shanhe-=+qiyunblog";
    
    public static String Md5Code(String password) {
        String saltPassword=salt+"/"+password;
        String md5Password = DigestUtils.md5DigestAsHex(saltPassword.getBytes());
        return md5Password;
    }
    
}
