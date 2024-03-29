package com.marshio.demo.controller;

import com.marshio.demo.annotation.DemoAnnotation;
import com.marshio.demo.annotation.StopWatch;
import com.marshio.demo.service.DemoService;
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

    private final DemoService demoService;

    @GetMapping("")
    @DemoAnnotation(value = "demo")
    @StopWatch(value = "demo")
    public String demo() {
        return demoService.demo();
    }
}
