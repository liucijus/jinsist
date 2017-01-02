package com.github.liucijus.jinsist.mock;

public interface StubCall<ReturnType, MockType> {
    ReturnType recordStub(MockType mock);
}
