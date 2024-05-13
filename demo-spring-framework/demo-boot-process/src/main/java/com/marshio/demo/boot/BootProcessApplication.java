package com.marshio.demo.boot;

import com.marshio.demo.boot.examples.ApplicationContextInitializerUsage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

        // 锁
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            while (true) {
                // 阻塞不释放
            }
        }).start();

        // 启动一个线程来模拟CPU使用率的飙升
        new Thread(() -> {
            while (!lock.tryLock()) {
                try {
                    Thread.sleep(10); // 休眠1秒钟
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                log.info("CPU使用率飙升");
            }
        }).start();
    }
}
