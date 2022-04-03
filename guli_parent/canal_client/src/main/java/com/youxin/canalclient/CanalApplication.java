package com.youxin.canalclient;

import com.youxin.canalclient.conf.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan("com.youxin")
public class CanalApplication implements CommandLineRunner {
    @Resource
    private CanalClient canalClient;
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        canalClient.run();
    }
}
