package com.github.liucijus.jinsist.integration.testtypes;

public class Collaborator {
    public String aMethodWithOneParam(String param) {
        throw new UnsupportedOperationException("aMethodWithOneParam was called from implementation.");
    }
}
