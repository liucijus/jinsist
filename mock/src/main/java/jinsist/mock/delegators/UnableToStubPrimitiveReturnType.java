package jinsist.mock.delegators;

public class UnableToStubPrimitiveReturnType extends RuntimeException {
    UnableToStubPrimitiveReturnType() {
        super("Methods with primitive return types must be stubbed to return value.");
    }
}
