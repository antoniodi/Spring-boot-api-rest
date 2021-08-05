package com.anthony.infrastructure.controllers.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class RestConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping( "/**" );  all endpoints
                registry.addMapping( "/**" )
                        .allowedOrigins( "http://localhost:8080/" )
                        .allowedMethods( "GET", "POST" );

            }
        };
    }

}
