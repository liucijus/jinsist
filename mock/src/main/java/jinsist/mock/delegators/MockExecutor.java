package jinsist.mock.delegators;

import jinsist.expectations.Expectations;
import jinsist.proxy.Delegator;

import java.lang.reflect.Method;

public class MockExecutor<MockType> implements Delegator<MockType> {
    private Expectations expectations;
    private Class<MockType> mockType;

    public MockExecutor(Expectations expectations, Class<MockType> mockType) {
        this.expectations = expectations;
        this.mockType = mockType;
    }

    @Override
    public Object handle(MockType instance, Method method, Object[] arguments) {
        return expectations.execute(mockType, instance, method, arguments);
    }
}
