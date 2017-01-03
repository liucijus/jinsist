package com.github.liucijus.jinsist.mock;

public class MethodToStubNotFound extends RuntimeException {
    MethodToStubNotFound() {
        super("Method not found while stubbing. Make sure public method is invoked under stubbing.");
    }
}
