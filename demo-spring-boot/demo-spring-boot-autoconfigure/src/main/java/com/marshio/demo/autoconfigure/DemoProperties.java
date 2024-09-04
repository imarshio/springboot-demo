package com.marshio.demo.autoconfigure;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/21 18:22
 */
@Configuration
@ConfigurationProperties(prefix = DemoProperties.PERFIX)
public class DemoProperties implements BeanClassLoaderAware, InitializingBean {
    public static final String PERFIX = "demo";

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
