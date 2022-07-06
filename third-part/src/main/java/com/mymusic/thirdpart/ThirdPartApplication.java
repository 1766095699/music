package com.mymusic.thirdpart;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;

@MapperScan(value = "com.mymusic",annotationClass = Mapper.class)
@SpringBootApplication(scanBasePackages = "com.mymusic")
@EnableDiscoveryClient
@FeignClient
public class ThirdPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThirdPartApplication.class, args);
    }

}
