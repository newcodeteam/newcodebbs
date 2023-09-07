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
    public static final String USER_USER_KEY = "user:token:";
    public static final Long USER_USER_TTL = 36000L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_SHOP_TTL = 30L;
    public static final String CACHE_SHOP_KEY = "cache:shop:";

    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";
    public static final String BLOG_LIKED_KEY = "blog:liked:";
    public static final String FEED_KEY = "feed:";
    public static final String SHOP_GEO_KEY = "shop:geo:";
    public static final String USER_SIGN_KEY = "sign:";
}
