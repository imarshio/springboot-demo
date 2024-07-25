package com.marshio.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marshio
 * @desc ...
 * @create 2024/5/14 10:18
 */
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {


    @GetMapping("")
    public String hello() {
        return "web interceptor test";
    }
}
