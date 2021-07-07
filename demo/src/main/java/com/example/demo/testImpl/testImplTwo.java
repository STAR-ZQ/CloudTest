package com.example.demo.testImpl;

import com.example.demo.testImpl.StrategyInteface;

public class testImplTwo implements StrategyInteface {
    @Override
    public String testMethod() {
        System.out.println("测试实现类接口2======================");return "测试实现类接口2======================";
    }
}
