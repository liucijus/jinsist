package com.github.liucijus.jinsist.proxy;

import java.lang.reflect.Method;

public interface Delegator {
    Object handle(Object instance, Method method, Object[] arguments);
}
