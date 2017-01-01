package com.github.liucijus.jinsist.mock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Invocation<MockType> {
    private final Class<MockType> mockClass;
    private final Class<?> instance;
    private final Method method;
    private final Object[] arguments;

    public Invocation(Class<MockType> mockClass, MockType instance, Method method, Object[] arguments) {
        this.mockClass = mockClass;
        this.instance = instance.getClass();
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return formatInvocation();
    }

    // todo extract and test
    private String formatInvocation() {
        String params = Arrays.stream(arguments).map(Object::toString).collect(Collectors.joining(", "));
        return mockClass.getSimpleName() + "." + method.getName() + "(" + params + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invocation that = (Invocation) o;

        if (!instance.equals(that.instance)) return false;
        if (!method.equals(that.method)) return false;
        return Arrays.equals(arguments, that.arguments);
    }

    @Override
    public int hashCode() {
        int result = instance.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }
}
