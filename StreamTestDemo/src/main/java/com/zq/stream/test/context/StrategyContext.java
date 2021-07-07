package com.zq.stream.test.context;

import com.zq.stream.test.StrategyInteface;
import com.zq.stream.test.emum.StrategyEnum;
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
