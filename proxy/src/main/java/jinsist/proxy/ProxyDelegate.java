package jinsist.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

public class ProxyDelegate<T> {
    private final Delegator<T> delegator;

    ProxyDelegate(Delegator<T> delegator) {
        this.delegator = delegator;
    }

    @RuntimeType
    public Object intercept(@AllArguments Object[] allArguments, @Origin Method method, @This Object instance)
            throws InvocationTargetException, IllegalAccessException {
        return delegator.handle((T) instance, method, allArguments);
    }
}
