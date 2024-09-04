package com.marshio.demo.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author marshio
 * @desc ...
 * @create 2024/8/21 18:16
 */
@Configuration
@Component
@ConditionalOnProperty
@ConditionalOnBean
@AutoConfigureAfter
public class DemoAutoConfigure {
}
