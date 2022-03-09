package com.inditex.challenge.config;

import com.inditex.challenge.utils.EnvironmentUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MainConfiguration {

    @Bean
    EnvironmentUtils environmentUtils(Environment env) {
        return new EnvironmentUtils(env);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
