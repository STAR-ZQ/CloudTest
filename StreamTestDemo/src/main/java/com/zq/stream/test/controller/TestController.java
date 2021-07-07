package com.zq.stream.test.controller;

import com.zq.stream.test.context.StrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private StrategyContext context;

    @RequestMapping("/testMethod")
    public String testMethod(String code) {
        return context.testMethod(code).testMethod();
    }
}
