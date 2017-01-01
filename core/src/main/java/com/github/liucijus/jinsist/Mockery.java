package com.github.liucijus.jinsist;

import com.github.liucijus.jinsist.mock.Mock;
import com.github.liucijus.jinsist.proxy.ProxyDelegate;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Mockery {
    private boolean hasExpectation = false;

    public void verify() {
        if (hasExpectation) {
            throw new UnmetExpectations();
        }
    }

    public <M> M mock(Class<M> classToMock) {
        try {
            return makeProxyFor(classToMock).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // todo wrap with Mockery exception
            throw new RuntimeException(e);
        }
    }

    private <M> Class<? extends M> makeProxyFor(Class<M> classToMock) {
        return new ByteBuddy()
                .subclass(classToMock)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(new ProxyDelegate(() -> this.hasExpectation = false)))
                .make()
                .load(classToMock.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

    }

    public <M> Mock<M> expect(M mock) {
        hasExpectation = true;
        return new Mock<>();
    }
}
