package com.marshio.demo.factory;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthCheckRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthCheckRoutePredicateFactory.Config> {

    public static final String PARAM_KEY = "param";

    public static final String REGEXP_KEY = "regexp";

    public AuthCheckRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_KEY, REGEXP_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return (GatewayPredicate) serverWebExchange -> {
            if (config.getName().equals(PARAM_KEY)) {
                return true;
            }
            return false;
        };
    }

    @Validated
    @Data
    public static class Config {
        private String name;
    }
}
