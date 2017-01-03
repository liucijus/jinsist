package com.github.liucijus.jinsist.expectations;

import com.github.liucijus.jinsist.matchers.Arguments;
import com.github.liucijus.jinsist.report.FormattedMethod;
import com.github.liucijus.jinsist.report.ReportEvent;

import java.lang.reflect.Method;

public class ExpectationEvent<MockType> implements ReportEvent {
    private final Class<MockType> classToMock;
    private final Method method;
    private final Arguments arguments;

    ExpectationEvent(Class<MockType> classToMock, Method method, Arguments arguments) {
        this.classToMock = classToMock;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return new FormattedMethod(classToMock, method, arguments.getArgumentMatchers()).toString();
    }
}
