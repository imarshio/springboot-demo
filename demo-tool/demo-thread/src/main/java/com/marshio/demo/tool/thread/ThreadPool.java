package com.marshio.demo.tool.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author marshio
 * @desc 线程池工具类
 * @create 2024/6/12 17:12
 */
public class ThreadPool {

    /**
     * 单一线程池
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor singleThreadPool(String threadNamePrefix) {
        // 最大和核心线程数都设置为 1
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new CustomizableThreadFactory(threadNamePrefix),
                // 由线程调用方直接运行，而不是新启线程
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ThreadPoolExecutor linkedBlockingQueueThreadPool(int corePoolSize, int maxPoolSize,
                                                                   long keepAliveTime, String threadNamePrefix) {
        // 最大和核心线程数都设置为 1
        // 线程的每次创建和销毁比较耗费资源，所以，这里设置一个合理的值，避免频繁创建和销毁
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new CustomizableThreadFactory(threadNamePrefix),
                // 由线程调用方直接运行，而不是新启线程
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
