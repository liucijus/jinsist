package com.github.liucijus.jinsist.mock;

class Voids<MockType> {
    Voids(ExpectationCall<MockType> call, Mock<MockType> mock) {
        call.recordStub(mock.setupInstance());
    }
}
