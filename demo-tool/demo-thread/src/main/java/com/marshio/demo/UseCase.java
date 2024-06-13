package com.marshio.demo;

import com.marshio.demo.tool.thread.ThreadPool;

import java.time.LocalDateTime;


/**
 * @author marshio
 * @desc ...
 * @create 2024/6/13 14:05
 */
public class UseCase {
    public static void main(String[] args) {
        var threadPoolExecutor = ThreadPool.singleThreadPool("test-");
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " start at " + LocalDateTime.now() + ":" + System.currentTimeMillis());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " end at " + LocalDateTime.now() + ":" + System.currentTimeMillis());
            });
        }

        var linkedBlockingQueueThreadPool = ThreadPool.linkedBlockingQueueThreadPool(
                Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors(),
                60 * 30L, "linkedBlockingQueueThreadPoolTest-");
        for (int i = 0; i < 10; i++) {
            linkedBlockingQueueThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + " start at " + LocalDateTime.now() + ":" + System.currentTimeMillis());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + " end at " + LocalDateTime.now() + ":" + System.currentTimeMillis());
            });
        }
    }
}
