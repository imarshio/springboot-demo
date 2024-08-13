package com.marshio.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/13 10:50
 */
@RestController
@RequestMapping("/v2")
@ConditionalOnProperty(name = "server.port", havingValue = "18081")
public class V2Controller {

    @Value("${server.port}")
    private String port;

    @GetMapping("/demo")
    public String demo() {
        return "v2 demo " + port;
    }
}
