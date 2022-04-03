package com.youxin.vods;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
@EnableDiscoveryClient
@EnableHystrix
@ComponentScan("com.youxin")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EduVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduVodApplication.class);
    }
}
