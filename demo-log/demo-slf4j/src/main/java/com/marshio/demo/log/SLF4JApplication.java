package com.marshio.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author marshio
 * @desc
 * @create 2024-01-04 18:42
 */
public class SLF4JApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SLF4JApplication.class);
        logger.info("Hello World");
    }
}
