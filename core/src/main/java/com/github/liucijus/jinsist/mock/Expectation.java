package com.github.liucijus.jinsist.mock;

import java.lang.reflect.Method;

public class Expectation {
    private final Object instance;
    private final Method method;
    private final Object[] arguments;
    private final Object result;

    public <ReturnType> Expectation(Object instance, Method method, Object[] arguments, ReturnType result) {

        this.instance = instance;
        this.method = method;
        this.arguments = arguments;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
