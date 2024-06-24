package com.marshio.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author marshio
 * @desc ...
 * @create 2024/6/24 18:08
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        // DocumentationType.OAS_30 表示这是 OpenAPI 3.0，另外它还支持 swagger 1.2 、swagger 2.0
        Docket docket = new Docket(DocumentationType.OAS_30)
                // 注册接口信息
                .apiInfo(apiInfo()).enable(true)
                .select()
                // apis： 添加swagger接口所在的 base包
                .apis(RequestHandlerSelectors.basePackage("com.marshio.demo.controller"))
                // 符合条件的路径,支持正则
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger - springfox 演示")
                .description("关于swagger的实现springfox的演示项目")
                // 作者
                .contact(new Contact("marshio", "https://marshio.com", "marshioman@gmail.com"))
                .version("v1.0")
                .build();
    }
}
