package com.example.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class AppConfig {

    @Bean
    public CustomPropertySource customPropertySource(ConfigurableEnvironment environment) {
        CustomPropertySource dynamicProperties = new CustomPropertySource("dynamicProperties");
        environment.getPropertySources().addLast(dynamicProperties);
        return dynamicProperties;
    }

    @Bean
    public PropertyLoaderService propertyLoaderService(CustomPropertySource customPropertySource) {
        return new PropertyLoaderService(customPropertySource);
    }


    @Bean
    public MyService myService(ConfigurableEnvironment environment) {
        return new MyService(environment);
    }
}

