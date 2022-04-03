package com.youxin.serviceucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@ComponentScan("com.youxin")
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@MapperScan("com.youxin.serviceucenter.mapper")
@EnableFeignClients
public class UcenterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterServiceApplication.class);
    }


}
