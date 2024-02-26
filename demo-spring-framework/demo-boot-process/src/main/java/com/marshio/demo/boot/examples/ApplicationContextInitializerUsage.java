package com.marshio.demo.boot.examples;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * @author marshio
 * @desc ...
 * @create 2024/2/23 16:50
 */
@Order(199)
public class ApplicationContextInitializerUsage implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    // 日志
    private static final Log log = LogFactory.getLog(ApplicationContextInitializerUsage.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // 在初始化容器之前，可以对容器进行一些初始化操作
        log.info("ApplicationContextInitializerUsage.initialize()");
        log.info("applicationContext is active " + applicationContext.isActive());
        log.info("applicationContext application name: " + applicationContext.getApplicationName());
        log.info("applicationContext display name :" + applicationContext.getDisplayName());
        log.info("applicationContext beanFactory : " + applicationContext.getBeanFactory());
        log.info("applicationContext beanDefinition count :" + applicationContext.getBeanDefinitionCount());
        log.info("applicationContext BeanDefinitionNames :" + Arrays.toString(applicationContext.getBeanDefinitionNames()));
        // log.info("applicationContext AutowireCapableBeanFactory :" + applicationContext.getAutowireCapableBeanFactory());
        log.info("applicationContext class loader :" + applicationContext.getClassLoader());
        log.info("applicationContext startup date :" + applicationContext.getStartupDate());
    }
}
