package com.marshio.demo.boot.examples;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component("annotationBeanDemo")
public class BootProcessComponent {

    private String name;
    private Integer age;

    @PostConstruct
    public void init() {
        this.name = "Demo init.";
        this.age = 20;
    }

    public BootProcessComponent() {
        log.info("BootProcessComponent 构造方法");
    }

    public String hello() {
        return this.getClass().getName() + this.name + ":" + this.age;
    }
}
