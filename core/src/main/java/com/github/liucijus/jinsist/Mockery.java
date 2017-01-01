package com.github.liucijus.jinsist;

import com.github.liucijus.jinsist.mock.Expectations;
import com.github.liucijus.jinsist.mock.Mock;
import com.github.liucijus.jinsist.proxy.Delegator;
import com.github.liucijus.jinsist.proxy.Proxy;

import java.util.HashMap;
import java.util.Map;

public class Mockery {
    private Map<Class<?>, Mock<?>> mocks = new HashMap<>();
    private Expectations expectations = new Expectations();

    public void verify() {
        expectations.verify();
    }

    public <MockType> MockType mock(Class<MockType> classToMock) {
        Delegator executor = (instance, method, arguments) -> {
            return expectations.execute(classToMock, (MockType) instance, method, arguments);
        };

        MockType instance = new Proxy<>(classToMock).instance(executor);
        Mock<MockType> mock = new Mock<>(classToMock, instance, expectations);
        mocks.put(instance.getClass(), mock);
        return instance;
    }

    public <M> Mock<M> expect(M mockInstance) {
        return findMock(mockInstance);
    }

    private <M> Mock<M> findMock(M mockInstance) {
        return (Mock<M>) mocks.get(mockInstance.getClass());
    }
}
