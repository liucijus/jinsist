package com.github.liucijus.jinsist.mock;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Expectations {
    private List<Expectation> expectations = new ArrayList<>();

    public <ReturnType, MockType> void recordStub(
            Class<MockType> classToMock,
            MockType instance,
            Method method,
            Object[] arguments,
            ReturnType result
    ) {
        Invocation invocation = new Invocation<>(classToMock, instance, method, arguments);
        expectations.add(new Expectation(invocation, result));
    }

    public <MockType> Object execute(
            Class<MockType> classToMock, MockType instance, Method method, Object[] arguments
    ) {
        Expectation expectation = expectations.remove(0);
        Invocation invocation = new Invocation<>(classToMock, instance, method, arguments);

        if (expectation.isFor(invocation)) {
            System.out.println(expectation);
            System.out.println(invocation);
            return expectation.getResult();
        } else
            throw new UnexpectedInvocation(expectation, invocation);
    }

    public boolean areExecuted() {
        return expectations.isEmpty();
    }
}
