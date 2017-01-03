package com.github.liucijus.jinsist.mock;

class Voids<MockType> {
    Voids(ExpectationCall<MockType> call, Mock<MockType> mock) {
        SetupResult setupResult = new SetupResult();
        call.recordStub(mock.setupInstance(setupResult));
        setupResult.verify();
    }
}
