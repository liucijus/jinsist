package jinsist.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Proxy<T> {
    private Class<T> type;

    private Class<? extends T> makeProxyFor(Class<T> classToMock, InstanceDelegator<T> delegator) {
        ProxyDelegate<T> delegate = new ProxyDelegate<>(delegator);
        return new ByteBuddy()
                .subclass(classToMock)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(delegate))
                .make()
                .load(classToMock.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

    }

    public Proxy(Class<T> type) {
        this.type = type;
    }

    public T instance(Delegator<T> delegator) {
        try {
            InstanceDelegator<T> instanceDelegator = new InstanceDelegator<>(delegator);
            T instance = makeProxyFor(type, instanceDelegator).newInstance();
            instanceDelegator.setInstance(instance);
            return instance;
        } catch (InstantiationException | IllegalAccessException | IllegalAccessError e) {
            throw new ProxyInstanceCreationFailed(type, e);
        }
    }

}

