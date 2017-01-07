package jinsist.mock;

public interface StubCall<ReturnType, MockType> {
    ReturnType recordStub(MockType mock);
}
