package com.marshio.demo.tool.thread;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/12 17:21
 */
@Data
public class CustomizableThreadFactory implements ThreadFactory {

    private String threadNamePrefix;
    private int threadPriority = 5;
    private boolean daemon = false;
    @Nullable
    private ThreadGroup threadGroup;
    private final AtomicInteger threadCount = new AtomicInteger();


    public CustomizableThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        // Thread 的核心参数
        // 1、Runnable
        // 2、线程名称
        // 3、线程优先级，默认优先级为 5
        // 4、守护线程，默认为非守护线程
        Thread thread = new Thread(this.getThreadGroup(), runnable, this.nextThreadName());
        thread.setPriority(this.getThreadPriority());
        thread.setDaemon(this.isDaemon());
        return thread;
    }

    protected String nextThreadName() {
        return this.getThreadNamePrefix() + this.threadCount.incrementAndGet();
    }
}
