package com.newcodebbs.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
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

    @Bean
    public RedissonClient redissonClient(){
        // 配置
        Config config = new Config();
//        配置redis的账号密码
        config.useSingleServer().setAddress("redis://192.168.230.129:6379").setPassword("123456");
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
