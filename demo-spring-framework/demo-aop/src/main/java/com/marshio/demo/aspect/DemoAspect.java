package com.marshio.demo.aspect;

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
public class DemoAspect {

    // @Pointcut() 声明一个切面，声明的方法返回必须为空

    @Pointcut("@annotation(com.marshio.demo.annotation.DemoAnnotation)")
    public void logAdvice() {
        // 切面方法应该为空
    }

    @Before("logAdvice()")
    public void before() {
        // 执行切面方法之前需要执行的
        log.info("执行切面方法之前...");
    }

    @After("logAdvice()")
    public void after() {
        // 执行切面方法之后需要执行的
        log.info("执行切面方法之后...");
    }

}
