package com.marshio.demo.boot.stream;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * @author marshio
 * @desc ...
 * @create 2024/5/9 19:51
 */
@Slf4j
public class StreamUsage {

    public static void main(String[] args) {
        // arrays 到100
        var stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
                49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100);

        // stream.parallel().forEach(i -> {
        //     log.info("{} {} {}", Thread.currentThread().getName(), LocalDateTime.now(), i);
        //     try {
        //         Thread.sleep(2000);
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        // });

        // ForkJoinPool 默认会启v-cpu（逻辑处理器）数量个线程，比如8核16线程的电脑默认会起16个线程执行任务
        // 也可以指定线程数量
        // new ForkJoinPool().submit(() -> stream.parallel().forEach(i -> {
        //     log.info("{} {} {}", Thread.currentThread().getName(), LocalDateTime.now(), i);
        //     try {
        //         Thread.sleep(2000);
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        // })).join();

        new ForkJoinPool(5).submit(() -> stream.parallel().forEach(i -> {
            log.info("{} {} {}", Thread.currentThread().getName(), LocalDateTime.now(), i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })).join();
    }
}
