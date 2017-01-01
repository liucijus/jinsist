package com.github.liucijus.jinsist.proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class ProxyDelegate {
    private Callable callable;

    public ProxyDelegate(Callable callable) {
        this.callable = callable;
    }

    @RuntimeType
    public Object intercept(@AllArguments Object[] allArguments, @Origin Method method)
            throws InvocationTargetException, IllegalAccessException {
        try {
            callable.call();
        } catch (Exception e) {
            // todo use Mockery exception
            throw new RuntimeException("failed to call callback");
        }
        return null;
    }
}
