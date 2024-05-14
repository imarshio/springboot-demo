package com.marshio.demo.boot;

import com.marshio.demo.boot.examples.ApplicationContextInitializerUsage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * @author marshio
 * @desc ...
 * @create 2024/2/2 18:23
 */
@Slf4j
@SpringBootApplication
public class BootProcessApplication {

    public static void main(String[] args) {
        // 启动入口
        // SpringApplication.run(BootProcessApplication.class, args);

        // 声明
        SpringApplication app = new SpringApplication(BootProcessApplication.class);

        // 注册启动器
        app.addInitializers(new ApplicationContextInitializerUsage());

        // 启动
        app.run();
    }
}
