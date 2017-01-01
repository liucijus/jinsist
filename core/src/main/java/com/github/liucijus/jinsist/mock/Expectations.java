package com.github.liucijus.jinsist.mock;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Expectations {
    private List<Expectation> expectations = new ArrayList<>();
    public <ReturnType> void recordStub(Object instance, Method method, Object[] arguments, ReturnType result) {
        expectations.add(new Expectation(instance, method, arguments, result));
    }

    public Object execute(Object instance, Method method, Object[] arguments) {
        return expectations.remove(0).getResult();
    }

    public boolean areExecuted() {
        return expectations.isEmpty();
    }
}
