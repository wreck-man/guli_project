package com.youxin.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.youxin"})
public class EasyExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyExcelApplication.class);
    }
}
