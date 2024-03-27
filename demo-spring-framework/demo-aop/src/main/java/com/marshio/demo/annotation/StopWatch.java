package com.marshio.demo.annotation;

import java.lang.annotation.*;

/**
 * @author marshio
 * @desc ...
 * @create 2024/3/27 14:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StopWatch {

    String value() default "";

    String job() default "";
}
