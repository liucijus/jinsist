package jinsist.expectations;

import jinsist.matchers.Arguments;

import java.lang.reflect.Method;

public interface Expectations {
    <ReturnType, MockType> void recordStub(
            Class<MockType> classToMock,
            MockType instance,
            Method method,
            Arguments arguments,
            ReturnType result
    );

    <MockType> Object execute(
            Class<MockType> classToMock,
            MockType instance,
            Method method,
            Object[] arguments
    );

    void verify();
}
