package com.marshio.demo.annotation.javax.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author marshio
 * @desc demo for javax.annotation.PostConstruct
 * @create 2024-02-02 16:15
 */
@Slf4j
@Service
public class PostConstructService {

    @PostConstruct
    public void init() {
        // after field injection completed
        log.info("init on PostConstruct");
    }

    public void demo() {
        log.info("demo on PostConstruct");
    }
}
