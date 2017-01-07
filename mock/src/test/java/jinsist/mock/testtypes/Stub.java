package jinsist.mock.testtypes;

import jinsist.matchers.Arguments;

import java.lang.reflect.Method;

public class Stub<MockType, ReturnType> {
    private final Class<MockType> classToMock;
    private final Method method;
    private final Arguments arguments;
    private final ReturnType result;

    public Stub(Class<MockType> classToMock, Method method, Arguments arguments, ReturnType result) {
        this.classToMock = classToMock;
        this.method = method;
        this.arguments = arguments;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stub<?, ?> stub = (Stub<?, ?>) o;

        if (!classToMock.equals(stub.classToMock)) return false;
        if (!method.equals(stub.method)) return false;
        if (!arguments.equals(stub.arguments)) return false;
        return result != null ? result.equals(stub.result) : stub.result == null;
    }

    @Override
    public int hashCode() {
        int result1 = classToMock.hashCode();
        result1 = 31 * result1 + method.hashCode();
        result1 = 31 * result1 + arguments.hashCode();
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Stub{" +
                "classToMock=" + classToMock +
                ", method=" + method +
                ", arguments=" + arguments +
                ", result=" + result +
                '}';
    }
}
