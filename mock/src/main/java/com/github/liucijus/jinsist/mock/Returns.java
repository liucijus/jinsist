package com.github.liucijus.jinsist.mock;

public class Returns<ReturnType, MockType> {
    private StubCall<ReturnType, MockType> call;
    private Mock<MockType> mock;

    Returns(StubCall<ReturnType, MockType> call, Mock<MockType> mock) {
        this.call = call;
        this.mock = mock;
    }

    public void returns(ReturnType output) {
        SetupResult setupResult = new SetupResult();
        call.recordStub(mock.setupInstanceWithResult(output, setupResult));
        setupResult.verify();
    }
}
