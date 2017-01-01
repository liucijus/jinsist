package com.github.liucijus.jinsist.mock;

import com.github.liucijus.jinsist.proxy.Delegator;
import com.github.liucijus.jinsist.proxy.Proxy;

public class Mock<MockType> {

    private Class<MockType> mockClass;
    private Expectations expectations;

    public Mock(Class<MockType> mockClass, Expectations expectations) {
        this.mockClass = mockClass;
        this.expectations = expectations;
    }

    public <ReturnType> Returns<ReturnType, MockType> stub(StubCall<ReturnType, MockType> call) {
        return new Returns<>(call, this);
    }

    public <ReturnType> MockType setupInstanceWithResult(ReturnType result) {
        Delegator expectationRecorder = ((instance, method, arguments) -> {
            expectations.recordStub(instance, method, arguments, result);
            return result;
        });

        return new Proxy<>(mockClass).instance(expectationRecorder);
    }
}
