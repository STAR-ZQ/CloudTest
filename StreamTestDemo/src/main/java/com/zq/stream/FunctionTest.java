package com.zq.stream;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionTest {
    Integer testFunctionInterface(Integer x, Function<Long, Long> function);
}
