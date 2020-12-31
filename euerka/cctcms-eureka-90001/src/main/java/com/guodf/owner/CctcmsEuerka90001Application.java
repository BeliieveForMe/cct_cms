package com.guodf.owner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CctcmsEuerka90001Application {

    public static void main(String[] args) {
        SpringApplication.run(CctcmsEuerka90001Application.class, args);
    }

}
