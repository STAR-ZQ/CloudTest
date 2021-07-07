package com.example.demo.emum;

public enum StrategyEnum {
    TESTMETHOD_ONE("com.example.demo.testImpl.testImplOne"),
    TESTMETHOD_TWO("com.example.demo.testImpl.testImplTwo");
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    StrategyEnum(String className) {
        this.setClassName(className);
    }
}
