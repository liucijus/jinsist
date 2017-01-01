package com.github.liucijus.jinsist.mock;

public class UnexpectedInvocation extends RuntimeException {
    public UnexpectedInvocation(Expectation expectation, Invocation invocation) {
        super("Expected " + expectation.getInvocation() + ", but was " + invocation);
    }
}
