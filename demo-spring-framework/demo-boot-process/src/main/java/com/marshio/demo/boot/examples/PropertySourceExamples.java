package com.marshio.demo.boot.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:/app.properties")
public class PropertySourceExamples {

    @Resource
    Environment env;

    @Bean
    public BootProcessComponent testBean() {
        BootProcessComponent component = new BootProcessComponent();
        component.setName(env.getProperty("annotationBeanDemo.name"));
        return component;
    }
}
