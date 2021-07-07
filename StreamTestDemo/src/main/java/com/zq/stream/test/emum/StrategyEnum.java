package com.zq.stream.test.emum;

public enum StrategyEnum {
    TESTMEHTOD_ONE("com.zq.stream.test.testImpl.testImplOne"),
    TESTMETHOD_TWO("com.zq.stream.test.testImpl.testImplTwo");
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
