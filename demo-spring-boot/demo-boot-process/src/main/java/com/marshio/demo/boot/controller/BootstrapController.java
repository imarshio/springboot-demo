package com.marshio.demo.boot.controller;

import com.marshio.demo.boot.examples.BootProcessComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marshio
 * @desc ...
 * @create 2024/5/14 10:18
 */
@RestController
@RequestMapping("/boot")
@RequiredArgsConstructor
public class BootstrapController {


    private final BootProcessComponent bootProcessComponent;

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring boot " + bootProcessComponent.hello();
    }
}
