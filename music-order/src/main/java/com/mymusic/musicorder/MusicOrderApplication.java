package com.mymusic.musicorder;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(value = "com.mymusic",annotationClass = Mapper.class)
@SpringBootApplication(scanBasePackages = "com.mymusic")
@EnableFeignClients
@EnableDiscoveryClient
//@MapperScan("com.mymusic.*.*")
//@ComponentScans(
//        value = {
//                @ComponentScan("com.mymusic.myrabbitmq.*"),
//                @ComponentScan("com.mymusic.musicorder.*")
//        }
//        )
public class MusicOrderApplication {


    public static void main(String[] args) {
//        System.out.println("hahaha"+);
        SpringApplication.run(MusicOrderApplication.class, args);
    }

}
