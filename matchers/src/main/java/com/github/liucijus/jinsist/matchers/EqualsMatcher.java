package com.github.liucijus.jinsist.matchers;

public class EqualsMatcher<T> implements ArgumentMatcher<T> {
    private T value;

    public EqualsMatcher(T value) {
        this.value = value;
    }

    @Override
    public boolean matches(T argument) {
        boolean isValueNull = value == null;
        boolean isArgumentNull = argument == null;

        return isArgumentNull && isValueNull || !isValueNull && value.equals(argument);
    }

    @Override
    public String toString() {
        return "EqualsMatcher " + value;
    }
}
