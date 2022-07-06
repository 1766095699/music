package com.mymusic.musicpoint;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(value = "com.mymusic.musicpoint.dao",annotationClass = Mapper.class)
@SpringBootApplication(scanBasePackages = {"com.mymusic.musicpoint","com.mymusic.myrabbitmq"})
//@MapperScan("com.mymusic.musicpoint.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class MusicPointApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicPointApplication.class, args);
    }

}
