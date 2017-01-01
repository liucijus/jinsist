package com.github.liucijus.jinsist.testtypes;

public class Collaborator {
    public String firstMethod(String param) {
        throw new UnsupportedOperationException("firstMethod was called from implementation.");
    }

    public String secondMethod(String param, String param2) {
        throw new UnsupportedOperationException("secondMethod was called from implementation.");
    }
}
