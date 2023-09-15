package com.newcodebbs.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * jwt 生成和解析
 */
public class JwtUtil {
    //    签名密钥
    private static String signKey = "new%code@bbs";
//    //    过期时间 毫秒 换算就是 24小时
//    private static Long expire = 86400000L;
    
    /**
     * 生成JWT令牌
     * @param claims JWT核心内容 给前端看的基本令牌内容
     * @return Token令牌
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000)))
                .compact();
        return jwt;
    }
    
    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return 公共json数据
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
