package com.marshio.demo.annotation.javax.PostConstruct;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author marshio
 * @desc ...
 * @create 2024/2/2 17:17
 */
@SpringBootTest
public class PostConstructServiceTest {

    @Autowired
    private PostConstructService demo;

    @Test
    public void test01() {
        demo.demo();
    }
}