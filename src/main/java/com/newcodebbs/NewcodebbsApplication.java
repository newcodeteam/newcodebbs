package com.newcodebbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.newcodebbs.mapper")
public class NewcodebbsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(NewcodebbsApplication.class, args);
    }
    
}
