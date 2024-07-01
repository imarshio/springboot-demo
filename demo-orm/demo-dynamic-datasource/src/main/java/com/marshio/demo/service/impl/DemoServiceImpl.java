package com.marshio.demo.service.impl;

import com.marshio.demo.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/7 11:20
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String demo() {
        return "demo";
    }
}
