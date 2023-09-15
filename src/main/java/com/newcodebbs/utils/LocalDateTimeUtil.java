package com.newcodebbs.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * date转换 localDatetime工具类
 * @author shanhe
 * @since 2023-09-10
 */
public class LocalDateTimeUtil {
    
//    public static final Date date = new Date();
    
//    /**
//     * 设置过期时间 7 天
//     * @param expireDate 过期时间
//     * @return 七天后的date
//     */
//    public static Date expireTime(Date expireDate){
//        Date expireTime = new Date(expireDate.getTime() + 7 * 24 * 3600 * 1000);
//        return expireDate;
//    }
    
//    /**
//     * date 转换 LocalDateTime
//     * @param expireTime date
//     * @return LocalDateTime
//     */
    
    /**
     * 获取七天后的时间
     * @return LocalDateTime 七天后时间
     */
    public static LocalDateTime localDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sevenDaysLater = now.plusDays(7);
        return sevenDaysLater;
    }
}
