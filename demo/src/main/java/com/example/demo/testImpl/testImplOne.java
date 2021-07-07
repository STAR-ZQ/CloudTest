package com.example.demo.testImpl;

import com.example.demo.testImpl.StrategyInteface;

public class testImplOne implements StrategyInteface {
    @Override
    public String testMethod() {
        System.out.println("测试实现类接口1======================");
        return "测试实现类接口1======================";
    }
}
