package com.github.liucijus.jinsist.mock;

import com.github.liucijus.jinsist.proxy.Delegator;
import com.github.liucijus.jinsist.proxy.Proxy;

public class Mock<MockType> {

    private Class<MockType> mockClass;
    private Object instance;
    private Expectations expectations;

    public Mock(Class<MockType> mockClass, Object instance, Expectations expectations) {
        this.mockClass = mockClass;
        this.instance = instance;
        this.expectations = expectations;
    }

    public <ReturnType> Returns<ReturnType, MockType> stub(StubCall<ReturnType, MockType> call) {
        return new Returns<>(call, this);
    }

    public <ReturnType> MockType setupInstanceWithResult(ReturnType result) {
        Delegator expectationRecorder = ((setupInstance, method, arguments) -> {
            expectations.recordStub(mockClass, (MockType) instance, method, arguments, result);
            return result;
        });

        return new Proxy<>(mockClass).instance(expectationRecorder);
    }
}
