package com.marshio.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 11:19
 */
@MapperScan({
        "com.marshio.demo.mapper",
})
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
