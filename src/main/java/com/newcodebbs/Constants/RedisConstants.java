package com.newcodebbs.Constants;

/**
 * @author shanhe
 * @since 2023-09-04
 */
public class RedisConstants {
    public static final String USER_CODE_KEY = "user:mail:";
    public static final Long USER_CODE_TTL = 5L;
    public static final String USER_CAPTCHA_KEY = "user:captcha:";
    public static final Long USER_CAPTCHA_TTL = 3L;
    public static final String USER_TOKEN_KEY = "user:token:";
    public static final String USER_EMAIL_USER_KEY = "user:email:user:";
    public static final Long USER_TOKEN_TTL = 1440L;
    public static final Long USER_EMAIL_TTL = 30L;
    
    public static final String USER_TOKEN_DATA = "user:token:ttl:";
    public static final Long USER_TOKEN_DATA_TTL = 7L;
    public static final Long CACHE_BODY_TTL = 7L;
    public static final String CACHE_BODY_KEY = "cache:body:";
    public static final String CACHE_lOCK_KEY = "cache:lock:";
    
    public static final Long CACHE_lOCK_TTL = 30L;
}
