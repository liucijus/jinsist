package com.github.liucijus.jinsist.matchers;

public interface ArgumentMatcher<A> {
    boolean matches(A argument);
}
