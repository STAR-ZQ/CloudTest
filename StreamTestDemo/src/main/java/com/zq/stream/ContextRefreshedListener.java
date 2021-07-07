package com.zq.stream;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    public static Map<String, Object> beanMap = new HashMap<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        beanMap = event.getApplicationContext().getBeansWithAnnotation(TestClass.class);
    }
}