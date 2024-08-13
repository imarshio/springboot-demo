package com.marshio.demo.boot.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component("cpuSpikeExample")
public class CPUSpikeExample {

    public static void main(String[] args) {
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
