package com.github.liucijus.jinsist.mock;

public class Mock<M> {
    public <A> Returns<A, M> stub(StubCall<A, M> call) {
        return new Returns<>();
    }
}
