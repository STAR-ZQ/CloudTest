package com.zq.stream.test.testImpl;

import com.zq.stream.test.StrategyInteface;

public class testImplTwo implements StrategyInteface {
    @Override
    public String testMethod() {
        System.out.println("测试实现类接口2======================");return "测试实现类接口2======================";
    }
}
