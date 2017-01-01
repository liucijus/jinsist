package com.github.liucijus.jinsist.proxy.testtypes;

public class ClassType {
    public String aMethod(String parameter) {
        throw new UnsupportedOperationException("aMethod was called from implementation.");
    }
}
