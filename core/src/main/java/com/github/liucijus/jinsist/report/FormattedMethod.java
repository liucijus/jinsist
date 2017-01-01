package com.github.liucijus.jinsist.report;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FormattedMethod {
    private final Class<?> type;
    private final Method method;
    private final Object[] arguments;

    public FormattedMethod(Class<?> type, Method method, Object[] arguments) {

        this.type = type;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return String.format("%s.%s(%s)", type.getSimpleName(), method.getName(), formatArguments(arguments));
    }

    private String formatArguments(Object[] arguments) {
        return Arrays.stream(arguments).map(Object::toString).collect(Collectors.joining(", "));
    }
}
