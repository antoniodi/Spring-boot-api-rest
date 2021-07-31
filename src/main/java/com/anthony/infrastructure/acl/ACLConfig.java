package com.anthony.infrastructure.acl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ACLConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
