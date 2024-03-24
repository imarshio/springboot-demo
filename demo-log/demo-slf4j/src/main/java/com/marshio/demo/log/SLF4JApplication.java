package com.marshio.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author marshio
 * @desc
 * @create 2024-01-04 18:42
 */
public class SLF4JApplication {

    private final static Logger logger = LoggerFactory.getLogger(SLF4JApplication.class);

    public static void main(String[] args) {

        // logger.
        logger.debug("Hello World");
    }
}
