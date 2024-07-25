package com.marshio.demo.config;

import com.marshio.demo.interceptor.DemoInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author marshio
 * @desc ...
 * @create 2024/7/25 18:17
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final DemoInterceptor demoInterceptor;

    public WebMvcConfiguration(DemoInterceptor demoInterceptor) {
        this.demoInterceptor = demoInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("");
    }
}
