package com.marshio.demo.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author marshio
 * @desc
 * @create 2023-12-29 18:18
 */
public class JCLApplication {
    private static final Log log = LogFactory.getLog(JCLApplication.class);

    public static void main(String[] args) {

        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }
}
