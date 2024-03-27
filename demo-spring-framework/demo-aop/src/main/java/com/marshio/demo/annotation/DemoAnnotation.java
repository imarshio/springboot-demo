package com.marshio.demo.annotation;

/**
 * @author marshio
 * @desc 自定义注解
 * @create 2024/3/27 13:35
 */
public @interface DemoAnnotation {

    String value() default "";
}
