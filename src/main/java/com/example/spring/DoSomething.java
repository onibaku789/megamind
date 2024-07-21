package com.example.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DoSomething {
    public static final Random random = new Random(1);
    private static final ThreadLocal<AnnotationConfigApplicationContext> THREAD_LOCAL_CONTEXT =
            ThreadLocal.withInitial(() -> new AnnotationConfigApplicationContext(AppConfig.class));

    public void main() {
        AnnotationConfigApplicationContext context = THREAD_LOCAL_CONTEXT.get();

        Map<String, Object> initialProperties = new HashMap<>();
        int i = random.nextInt(10_000);
        initialProperties.put("myapp.username", "initialUsername" + i);
        initialProperties.put("myapp.password", "initialPassword" + i);
        PropertyLoaderService propertyLoader = context.getBean(PropertyLoaderService.class);
        propertyLoader.loadProperties(initialProperties);
        MyService myService = context.getBean(MyService.class);
        myService.performAction();
    }

}
