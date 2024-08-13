package com.marshio.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marshio
 * @desc ...
 * @create 2024/3/27 13:20
 */
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {


    @GetMapping("/hello")
    public String demo() {
        return "demo";
    }
}
