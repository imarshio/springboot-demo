package com.marshio.demo.autoconfigure;

import java.util.logging.Logger;

public class DemoAutoConfigurationService {

    private static final Logger logger = Logger.getLogger(DemoAutoConfigurationService.class.getName());

    private final DemoProperties properties;

    public DemoAutoConfigurationService(DemoProperties properties) {
        logger.info("DemoAutoConfigurationService init");
        this.properties = properties;
    }

    public String location() {
        return "Mapper location is " + properties.getMapperLocations();
    }
}
