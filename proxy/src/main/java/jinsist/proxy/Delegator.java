package jinsist.proxy;

import java.lang.reflect.Method;

public interface Delegator<T> {
    Object handle(T instance, Method method, Object[] arguments);
}
