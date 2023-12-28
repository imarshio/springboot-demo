package com.marshio.demo.log;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author marshio
 * @desc
 * @create 2023-12-20 19:06
 */
@SpringBootApplication
public class Log4jApplication {

    static Logger logger = Logger.getLogger(Log4jApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Log4jApplication.class, args);
        printTest();
    }

    private static void printTest() {
        logger.debug("this is an debug level log.");
        logger.info("this is an info level log.");
        logger.warn("this is an warn level log.");
        logger.error("this is an error level log.");
        logger.fatal("this is an fatal level log.");
    }
}
