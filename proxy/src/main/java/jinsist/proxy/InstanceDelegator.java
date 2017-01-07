package jinsist.proxy;

import java.lang.reflect.Method;

class InstanceDelegator {
    private Object instance;
    private Delegator delegator;

    InstanceDelegator(Delegator delegator) {
        this.delegator = delegator;
    }

    void setInstance(Object instance) {
        this.instance = instance;
    }

    Object handle(Method method, Object[] arguments) {
        return delegator.handle(instance, method, arguments);
    }
}
