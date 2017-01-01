package com.github.liucijus.jinsist.proxy;

import com.github.liucijus.jinsist.proxy.testtypes.ClassType;
import com.github.liucijus.jinsist.proxy.testtypes.InterfaceType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProxyTest {
    private Delegator nullDelegator = (instance, method, arguments) -> null;

    @Test
    public void createsClassProxy() {
        ClassType classProxy = new Proxy<>(ClassType.class).instance(nullDelegator);

        assertIsSubclass(ClassType.class, classProxy);
    }

    @Test
    public void createsInterfaceProxy() {
        InterfaceType interfaceProxy = new Proxy<>(InterfaceType.class).instance(nullDelegator);

        assertHasInterface(InterfaceType.class, interfaceProxy);
    }

    @Test(expected = ProxyInstanceCreationFailed.class)
    public void failsCreatingNonPublicInterface() {
        new Proxy<>(InnerInterface.class).instance(nullDelegator);
    }

    @Test
    public void delegatesMethodCall() {
        Delegator firstArgumentDelegator = (instance, method, arguments) -> arguments[0];

        InterfaceType interfaceProxy = new Proxy<>(InterfaceType.class).instance(firstArgumentDelegator);

        assertEquals("value", interfaceProxy.aMethod("value"));
    }

    private <T> void assertIsSubclass(Class<T> type, T implementation) {
        assertEquals("Does not subclass " + type, type, implementation.getClass().getSuperclass());
    }

    private <T> void assertHasInterface(Class<T> type, T implementation) {
        assertTrue("Does not implement " + type, type.isInstance(implementation));
    }

    interface InnerInterface {
    }
}
