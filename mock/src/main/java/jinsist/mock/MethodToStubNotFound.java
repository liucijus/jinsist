package jinsist.mock;

class MethodToStubNotFound extends RuntimeException {
    MethodToStubNotFound() {
        super("Method not found while stubbing. Make sure public method is invoked under stubbing.");
    }
}
