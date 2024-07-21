package com.example.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import org.springframework.core.env.Environment;

import java.util.stream.IntStream;

@Service

public class MyService {

    private static final Logger log = LoggerFactory.getLogger(MyService.class);
    private final Environment environment;

    public MyService(Environment environment) {
        this.environment = environment;
    }

    public void performAction() {
        IntStream.range(0, 10).parallel()
                .forEach(value -> extracted());

    }

    private void extracted() {
        String username = environment.getProperty("myapp.username");
        String password = environment.getProperty("myapp.password");
        log.info("Username: {}", username);
        log.info("Password: {}", password);
    }
}
