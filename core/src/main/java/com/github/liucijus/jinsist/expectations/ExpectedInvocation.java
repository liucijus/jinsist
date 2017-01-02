package com.github.liucijus.jinsist.expectations;

import com.github.liucijus.jinsist.matchers.Arguments;
import com.github.liucijus.jinsist.report.FormattedMethod;

import java.lang.reflect.Method;

public class ExpectedInvocation<MockType> {
    private final Class<MockType> mockClass;
    private final Class<?> instance;
    private final Method method;
    private final Arguments arguments;

    ExpectedInvocation(Class<MockType> mockClass, MockType instance, Method method, Arguments arguments) {
        this.mockClass = mockClass;
        this.instance = instance.getClass();
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return formatInvocation();
    }

    private String formatInvocation() {
        return new FormattedMethod(mockClass, method, arguments.getArgumentMatchers()).toString();
    }

    Class<?> getInstance() {
        return instance;
    }

    Method getMethod() {
        return method;
    }

    Arguments getArguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpectedInvocation<?> that = (ExpectedInvocation<?>) o;

        if (!mockClass.equals(that.mockClass)) return false;
        if (!instance.equals(that.instance)) return false;
        if (!method.equals(that.method)) return false;
        return arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
        int result = mockClass.hashCode();
        result = 31 * result + instance.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }
}
