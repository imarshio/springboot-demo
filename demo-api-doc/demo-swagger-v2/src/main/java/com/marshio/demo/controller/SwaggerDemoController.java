package com.marshio.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author marshio
 * @desc
 * @create 2023-07-05 16:16
 */
@RestController
public class SwaggerDemoController {

    //
    @GetMapping
    public String getDemoController() {
        return "";
    }

    @PostMapping
    public String postDemoController() {
        return "";
    }

    @PutMapping
    public String putDemoController() {
        return "";
    }

    @DeleteMapping
    public String deleteDemoController() {
        return "";
    }

    // @Mapping
    // public String getDemoController() {
    //     return "";
    // }
    //
    // @GetMapping
    // public String getDemoController() {
    //     return "";
    // }
}
