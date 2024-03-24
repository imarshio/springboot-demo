package com.marshio.demo.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author marshio
 * @desc
 * @create 2023-12-20 19:06
 */
public class Log4j2Application {

    private final static Logger logger = LogManager.getLogger(Log4j2Application.class);

    public static void main(String[] args) {
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
