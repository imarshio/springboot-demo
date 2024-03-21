package com.marshio.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author marshio
 * @desc
 * @create 2023-12-20 19:06
 */
public class LogbackApplication {


    private final static Logger logger = LoggerFactory.getLogger(LogbackApplication.class);

    public static void main(String[] args) {
        printTest();
    }

    private static void printTest() {
        logger.debug("this is an debug level log.");
        logger.info("this is an info level log.");
        logger.warn("this is an warn level log.");
        logger.error("this is an error level log.");
    }
}