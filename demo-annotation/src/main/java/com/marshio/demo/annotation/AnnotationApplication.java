package com.marshio.demo.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author marshio
 * @desc
 * @create 2023-12-26 16:39
 */
@SpringBootApplication
@MapperScan("com.marshio.demo.annotation")
public class AnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationApplication.class, args);
    }
}
