package com.github.liucijus.jinsist;

import com.github.liucijus.jinsist.mock.Mock;
import com.github.liucijus.jinsist.proxy.Delegator;
import com.github.liucijus.jinsist.proxy.Proxy;

public class Mockery {
    private boolean hasExpectation = false;

    public void verify() {
        if (hasExpectation) {
            throw new UnmetExpectations();
        }
    }

    public <M> M mock(Class<M> classToMock) {
        Delegator expectationWatcher = (instance, method, arguments) -> {
            this.hasExpectation = false;
            return null;
        };

        return new Proxy<>(classToMock).instance(expectationWatcher);
    }

    public <M> Mock<M> expect(M mock) {
        hasExpectation = true;
        return new Mock<>();
    }
}
