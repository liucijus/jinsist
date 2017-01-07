package jinsist.proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyDelegate<T> {
    private InstanceDelegator<T> delegator;

    ProxyDelegate(InstanceDelegator<T> delegator) {
        this.delegator = delegator;
    }

    @RuntimeType
    public Object intercept(@AllArguments Object[] allArguments, @Origin Method method)
            throws InvocationTargetException, IllegalAccessException {
        return delegator.handle(method, allArguments);
    }
}
