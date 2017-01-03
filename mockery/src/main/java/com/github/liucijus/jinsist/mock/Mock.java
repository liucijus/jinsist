package com.github.liucijus.jinsist.mock;

import com.github.liucijus.jinsist.expectations.Expectations;
import com.github.liucijus.jinsist.matchers.Arguments;
import com.github.liucijus.jinsist.matchers.EqualsMatcher;
import com.github.liucijus.jinsist.proxy.Delegator;
import com.github.liucijus.jinsist.proxy.Proxy;

import java.lang.reflect.Method;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Mock<MockType> {

    private Class<MockType> mockClass;

    public MockType getInstance() {
        return instance;
    }

    private MockType instance;
    private Expectations expectations;

    public Mock(Class<MockType> mockClass, Expectations expectations) {
        Delegator<MockType> executor = (instance, method, arguments) -> {
            return expectations.execute(mockClass, instance, method, arguments);
        };

        MockType instance = new Proxy<>(mockClass).instance(executor);
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
        Delegator<MockType> expectationRecorder = ((setupInstance, method, args) -> {
            verifyReturnTypeNeedsToBeStubbed(result, method);

            Arguments arguments = new Arguments(
                    stream(args).map(EqualsMatcher::new).collect(toList())
            );

            expectations.recordStub(mockClass, instance, method, arguments, result);
            return result;
        });

        return new Proxy<>(mockClass).instance(expectationRecorder);
    }

    private <ReturnType> void verifyReturnTypeNeedsToBeStubbed(ReturnType result, Method method) {
        Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE) && returnType.isPrimitive() && result == null) {
            throw new UnableToStubPrimitiveReturnType();
        }
    }

    MockType setupInstance() {
        return setupInstanceWithResult(null);
    }
}
