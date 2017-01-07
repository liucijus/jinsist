package jinsist.mock;

import jinsist.expectations.Expectations;
import jinsist.mock.delegators.MockExecutor;
import jinsist.mock.delegators.SetupRecorder;
import jinsist.proxy.Delegator;
import jinsist.proxy.Proxy;

public class Mock<MockType> {

    private Class<MockType> mockClass;

    public MockType getInstance() {
        return instance;
    }

    private MockType instance;
    private Expectations expectations;

    public Mock(Class<MockType> mockClass, Expectations expectations) {
        MockType instance = new Proxy<>(mockClass).instance(new MockExecutor<>(expectations, mockClass));
        this.mockClass = mockClass;
        this.instance = instance;
        this.expectations = expectations;
    }

    public <ReturnType> Returns<ReturnType, MockType> stub(StubCall<ReturnType, MockType> call) {
        return new Returns<>(call, this);
    }

    public Voids<MockType> call(ExpectationCall<MockType> call) {
        return new Voids<>(call, this);
    }

    <ReturnType> MockType setupInstanceWithResult(ReturnType result, SetupResult setupResult) {
        Delegator<MockType> recorder = new SetupRecorder<>(expectations, mockClass, instance, result, setupResult);

        return new Proxy<>(mockClass).instance(recorder);
    }

    MockType setupInstance(SetupResult setupResult) {
        return setupInstanceWithResult(null, setupResult);
    }
}
