package com.youxin.servicestatistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan(basePackages = "com.youxin.servicestatistics.mapper")
@ComponentScan("com.youxin")
@SpringBootApplication
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class);
    }
}
