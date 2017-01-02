package com.github.liucijus.jinsist.matchers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualsMatcherTest {
    @Test
    public void matchesNull() {
        ArgumentMatcher<String> equals = new EqualsMatcher<>(null);

        assertTrue(equals.matches(null));
    }

    @Test
    public void doesNotMatchNonEqualObjects() {
        ArgumentMatcher<String> equals = new EqualsMatcher<>("value");

        assertFalse(equals.matches("different value"));
    }

    @Test
    public void matchesPrimitiveValues() {
        ArgumentMatcher<Integer> equals = new EqualsMatcher<>(1);

        assertTrue(equals.matches(1));
    }
}
