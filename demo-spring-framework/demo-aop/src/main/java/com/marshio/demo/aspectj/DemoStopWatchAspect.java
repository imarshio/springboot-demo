package com.marshio.demo.aspectj;

import com.marshio.demo.annotation.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
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
public class DemoStopWatchAspect {

    // @Pointcut() 声明一个切面，声明的方法返回必须为空

    @Pointcut("@annotation(com.marshio.demo.annotation.StopWatch)")
    public void stopWatchAdvice() {
        // 切面方法体应该为空
    }

    @Around("stopWatchAdvice()")
    public Object before(ProceedingJoinPoint point) {
        point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();

        StopWatch stopWatch = signature.getMethod().getAnnotation(StopWatch.class);
        Object result;
        try {
            org.springframework.util.StopWatch watch = new org.springframework.util.StopWatch();
            watch.start();
            // 在这之前执行的相当于@Before
            log.info("1、{}方法执行开始", stopWatch.value());
            result = point.proceed();
            watch.stop();
            log.info("2、{}方法执行耗时：{} ms", stopWatch.value(), watch.getTotalTimeMillis());
            // 这里执行相当于@AfterReturn，返回结果之后，
            log.info("3、{}方法执行结束", stopWatch.value());
        } catch (Throwable e) {
            // 这里执行的相当于@AfterThrowing
            log.error("4、{}方法执行异常", stopWatch.value());
            throw new RuntimeException(e);
        } finally {
            // 这里执行的相当于@After，方法执行完
            log.info("4、{}方法执行完成", stopWatch.value());
        }
        log.info("5、{}方法执行结果为：{}", stopWatch.value(), result);
        return result;
    }

}
