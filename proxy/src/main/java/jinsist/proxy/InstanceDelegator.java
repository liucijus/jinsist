package jinsist.proxy;

import java.lang.reflect.Method;

class InstanceDelegator<T> {
    private T instance;
    private Delegator<T> delegator;

    InstanceDelegator(Delegator<T> delegator) {
        this.delegator = delegator;
    }

    void setInstance(T instance) {
        this.instance = instance;
    }

    Object handle(Method method, Object[] arguments) {
        return delegator.handle(instance, method, arguments);
    }
}
