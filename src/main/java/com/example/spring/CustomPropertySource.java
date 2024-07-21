package com.example.spring;

import org.springframework.core.env.EnumerablePropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomPropertySource extends EnumerablePropertySource<Map<String, Object>> {

    private final Map<String, Object> properties;

    public CustomPropertySource(String name) {
        super(name, new HashMap<>());
        properties = new ConcurrentHashMap<>();
    }

    public void updateProperties(Map<String, Object> newProperties) {
        properties.putAll(newProperties);
    }

    @Override
    public String[] getPropertyNames() {
        return properties.keySet().toArray(new String[0]);
    }

    @Override
    public Object getProperty(String name) {
        return properties.get(name);
    }
}