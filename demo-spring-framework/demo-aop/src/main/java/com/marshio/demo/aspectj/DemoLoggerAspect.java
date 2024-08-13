package com.marshio.demo.aspectj;

import com.marshio.demo.domain.entity.User;
import com.marshio.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
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
@RequiredArgsConstructor
public class DemoLoggerAspect {

    private final IUserService service;

    // @Pointcut() 声明一个切面，声明的方法返回必须为空
    // https://docs.spring.io/spring-framework/reference/core/aop/ataspectj/pointcuts.html

    @Pointcut("@annotation(com.marshio.demo.annotation.DemoAnnotation)")
    public void logAdvice() {
        // 切面方法应该为空
    }

    @Pointcut("execution(public * com.marshio.demo.service.IUserService.update(..) )")
    public void logAdvice2() {
        // 切面方法应该为空
    }

    // https://docs.spring.io/spring-framework/reference/core/aop/ataspectj/advice.html#aop-advice-after-throwing
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
        // 执行切面方法之后需要执行的  == finally块
        log.info("3、执行切面方法之后...");
    }

    @Around(value = "logAdvice2()")
    public Object saveLog(ProceedingJoinPoint pjp) throws Throwable {
        // 项目生产配置
        Object[] args = pjp.getArgs();
        Object result;
        try {
            result = pjp.proceed(args);
            service.save((User) args[0]);
        } catch (Throwable t) {
            log.error("LogAspect saveLog error {}", t.getMessage(), t);
            throw t;
        } finally {
            // do nothing
        }
        return result;
    }
}
