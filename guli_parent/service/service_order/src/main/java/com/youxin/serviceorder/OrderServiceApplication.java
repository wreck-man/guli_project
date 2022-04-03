package com.youxin.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages ="com.youxin")
@MapperScan(basePackages = "com.youxin.serviceorder.mapper")
public class OrderServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(OrderServiceApplication.class);
    }
}
