package com.zjy.filesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhaojianyu
 */
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FilesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesystemApplication.class, args);
    }

}

