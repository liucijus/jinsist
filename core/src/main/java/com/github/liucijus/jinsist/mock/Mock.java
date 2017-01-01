package com.github.liucijus.jinsist.mock;

import com.github.liucijus.jinsist.proxy.Delegator;
import com.github.liucijus.jinsist.proxy.Proxy;

import java.lang.reflect.Method;

public class Mock<MockType> {

    private Class<MockType> mockClass;
    private MockType instance;
    private Expectations expectations;

    public Mock(Class<MockType> mockClass, MockType instance, Expectations expectations) {
        this.mockClass = mockClass;
        this.instance = instance;
        this.expectations = expectations;
    }

    public <ReturnType> Returns<ReturnType, MockType> stub(StubCall<ReturnType, MockType> call) {
        return new Returns<>(call, this);
    }

    public Voids<MockType> call(ExpectationCall<MockType> call) {
        return new Voids<>(call, this);
    }

    <ReturnType> MockType setupInstanceWithResult(ReturnType result) {
        Delegator<MockType> expectationRecorder = ((setupInstance, method, arguments) -> {
            verifyReturnTypeNeedsToBestubbed(result, method);
            expectations.recordStub(mockClass, instance, method, arguments, result);
            return result;
        });

        return new Proxy<>(mockClass).instance(expectationRecorder);
    }

    private <ReturnType> void verifyReturnTypeNeedsToBestubbed(ReturnType result, Method method) {
        Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE) && returnType.isPrimitive() && result == null) {
            throw new UnableToStubPrimitiveReturnType();
        }
    }

    MockType setupInstance() {
        return setupInstanceWithResult(null);
    }
}
