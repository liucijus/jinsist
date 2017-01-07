package jinsist.expectations;

import jinsist.report.FormattedMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Invocation<MockType> {
    private final Class<MockType> mockClass;
    private final Class<?> instance;
    private final Method method;
    private final Object[] arguments;

    Invocation(Class<MockType> mockClass, MockType instance, Method method, Object[] arguments) {
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
        return new FormattedMethod(mockClass, method, Arrays.asList(arguments)).toString();
    }

    Class<?> getInstance() {
        return instance;
    }

    Method getMethod() {
        return method;
    }

    Object[] getArguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invocation that = (Invocation) o;

        return instance.equals(that.instance) && method.equals(that.method) && Arrays.equals(arguments, that.arguments);
    }

    @Override
    public int hashCode() {
        int result = instance.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }

}
