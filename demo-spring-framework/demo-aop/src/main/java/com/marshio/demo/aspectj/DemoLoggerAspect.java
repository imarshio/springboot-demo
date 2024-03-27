package com.marshio.demo.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author marshio
 * @desc ...
 * @create 2024/3/27 13:00
 */
// @Aspect声明这是一个切面类
@Slf4j
@Aspect
@Component
public class DemoLoggerAspect {

    // @Pointcut() 声明一个切面，声明的方法返回必须为空

    @Pointcut("@annotation(com.marshio.demo.annotation.DemoAnnotation)")
    public void logAdvice() {
        // 切面方法应该为空
    }

    @Before("logAdvice()")
    public void before() {
        // 执行切面方法之前需要执行的
        log.info("1、执行切面方法之前...");
    }

    @AfterReturning("logAdvice()")
    public void afterReturning() {
        // 执行切面方法之后需要执行的
        log.info("2、执行切面方法之后，结果返回之前...");
    }

    @AfterThrowing("logAdvice()")
    public void afterThrowing() {
        // 执行切面方法之后需要执行的
        log.info("4、执行切面方法之后，异常抛出之后...");
    }

    @After("logAdvice()")
    public void after() {
        // 执行切面方法之后需要执行的
        log.info("3、执行切面方法之后...");
    }
}
