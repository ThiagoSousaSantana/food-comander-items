package com.foodcomander.items.settings.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Controller
@EnableSwagger2
public class SwaggerSettings {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.foodcomander.items"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Food Comander Item Service",
                "A microservice of Food Comander.",
                "0.1",
                "Terms of service",
                new Contact("people", "https://github.com/ThiagoSousaSantana/food-comander-items", "people@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

}
