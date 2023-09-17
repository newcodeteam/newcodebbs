package com.newcodebbs.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *  redis客户端配置类
 * </p>
 *
 * @author shanhe
 * @since 2023-09-04
 */
@Configuration
public class RedissonConfig {
    
    // redis地址
    @Value("${spring.redis.host}")
    private String redisAddr;
    
    // redis端口
    @Value("${spring.redis.port:6379}")
    private String redisPort;
    
    // redis密码
    @Value("${spring.redis.password:null}")
    private String redisPassword;

    @Bean
    public RedissonClient redissonClient(){
        // 配置
        Config config = new Config();
//        配置redis的账号密码
        if (redisPassword.equals("null")) {
            config.useSingleServer().setAddress("redis://"+redisAddr+":"+redisPort).setPassword(redisPassword);
        } else {
            config.useSingleServer().setAddress("redis://"+redisAddr+":"+redisPort);
        }
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
