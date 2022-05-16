package com.santander.context;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ScenarioContext {

    private final ConcurrentMap<String, Object> context = new ConcurrentHashMap<>();

    public <T> void setInContext(String name, Object value) {
        context.put(name, value);
    }

    public <T> T getFromContext(String name, Class<T> clazz) {
        return (T) context.get(name);
    }

    public void clearContext() {
        context.clear();
    }
}
