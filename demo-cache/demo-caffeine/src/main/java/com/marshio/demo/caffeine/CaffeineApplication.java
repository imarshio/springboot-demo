package com.marshio.demo.caffeine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author marshio
 * @desc
 * @create 2023-03-31 13:00
 */
@SpringBootApplication(scanBasePackages = "com.marshio.demo")
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class CaffeineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaffeineApplication.class, args);
    }

}
