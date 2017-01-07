package jinsist.proxy;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyDelegate {
    private InstanceDelegator delegator;

    ProxyDelegate(InstanceDelegator delegator) {
        this.delegator = delegator;
    }

    @RuntimeType
    public Object intercept(@AllArguments Object[] allArguments, @Origin Method method)
            throws InvocationTargetException, IllegalAccessException {
        return delegator.handle(method, allArguments);
    }
}
