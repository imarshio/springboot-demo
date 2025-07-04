package com.marshio.demo.controller;

import com.marshio.demo.autoconfigure.DemoAutoConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final DemoAutoConfigurationService demoAutoConfigurationService;

    @GetMapping("/location")
    public String location() {
        return demoAutoConfigurationService.location();
    }

}
