package com.github.liucijus.jinsist.expectations;

import com.github.liucijus.jinsist.report.FormattedMethod;
import com.github.liucijus.jinsist.report.ReportEvent;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ExecuteEvent<MockType> implements ReportEvent {
    private final Class<MockType> mockClass;
    private final Method method;
    private final Object[] arguments;

    ExecuteEvent(Class<MockType> mockClass, Method method, Object[] arguments) {
        this.mockClass = mockClass;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return new FormattedMethod(mockClass, method, Arrays.asList(arguments)).toString();
    }
}
