package jinsist.expectations;

public class UnexpectedInvocation extends RuntimeException {
    UnexpectedInvocation(Invocation invocation) {
        super("Nothing expected, but was " + invocation);
    }

    UnexpectedInvocation(Expectation expectation, Invocation invocation) {
        super("Expected " + expectation.getExpectedInvocation() + ", but was " + invocation);
    }

    UnexpectedInvocation(String message, UnexpectedInvocation e) {
        super(message, e);
    }
}
