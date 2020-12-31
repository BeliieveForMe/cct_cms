package com.guodf.owner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.guodf.owner.mapper")
public class BasicInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicInfoApplication.class, args);
    }

}
