package com.marshio.demo.mail.tools;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author marshio
 * @desc
 * @create 2023-03-29 12:34
 */
@Component
public class ThreadPoolUtil {

    private static ThreadPoolUtil instance;

    private ThreadPoolExecutor threadPool;

    public static ThreadPoolUtil getInstance() {
        // 实例获取的唯一入口
        if (instance == null) {
            instance = new ThreadPoolUtil();
        }
        return instance;
    }

    // 构造方式私有化，防止破坏单例
    private ThreadPoolUtil() {
        // 初始化线程池
        initThreadPool();
    }

    private void initThreadPool() {
        // 丢弃策略的线程池
        threadPool = new ThreadPoolExecutor(
                10,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    public ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            initThreadPool();
        }
        return threadPool;
    }

}
