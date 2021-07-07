package com.example.demo.context;

import com.example.demo.testImpl.StrategyInteface;
import com.example.demo.emum.StrategyEnum;
import org.springframework.stereotype.Component;

@Component
public class StrategyContext {

    public  StrategyInteface testMethod(String code) {
        String className = StrategyEnum.valueOf(code).getClassName();
        try {
            return (StrategyInteface) Class.forName(className).newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
