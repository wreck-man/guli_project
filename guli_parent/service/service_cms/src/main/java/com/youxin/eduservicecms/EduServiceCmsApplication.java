package com.youxin.eduservicecms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@MapperScan(basePackages = "com.youxin.eduservicecms.mapper")
@ComponentScan("com.youxin")
@SpringBootApplication
public class EduServiceCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduServiceCmsApplication.class);
    }
}
