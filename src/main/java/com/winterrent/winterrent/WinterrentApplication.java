package com.winterrent.winterrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {
//        "com.winterrent.winterrent.rest",
//        "com.winterrent.winterrent.dao",
//        "com.winterrent.winterrent.entity",
//        "com.winterrent.winterrent.service",
//        "com.winterrent.winterrent.configuration"
//})
public class WinterrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinterrentApplication.class, args);
    }

}

