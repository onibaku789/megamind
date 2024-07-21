package com.example.spring;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PropertyLoaderService {

    private final CustomPropertySource customPropertySource;

    public PropertyLoaderService(CustomPropertySource customPropertySource) {
        this.customPropertySource = customPropertySource;
    }

    public void loadProperties(Map<String, Object> newProperties) {
        customPropertySource.updateProperties(newProperties);
    }
}