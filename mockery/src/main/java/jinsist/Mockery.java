package jinsist;

import jinsist.expectations.Expectations;
import jinsist.expectations.OrderedExpectations;
import jinsist.expectations.ReportExpectations;
import jinsist.mock.Mock;

import java.util.HashMap;
import java.util.Map;

public class Mockery {
    private Map<Class<?>, Mock<?>> mocks = new HashMap<>();
    private Expectations expectations = new ReportExpectations(new OrderedExpectations());

    public void verify() {
        expectations.verify();
    }

    public <MockType> MockType mock(Class<MockType> classToMock) {
        Mock<MockType> mock = new Mock<>(classToMock, expectations);
        MockType instance = mock.getInstance();
        mocks.put(instance.getClass(), mock);
        return instance;
    }

    public <M> Mock<M> expect(M mockInstance) {
        return findMock(mockInstance);
    }

    private <M> Mock<M> findMock(M mockInstance) {
        return (Mock<M>) mocks.get(mockInstance.getClass());
    }
}
