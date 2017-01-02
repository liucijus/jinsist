package com.github.liucijus.jinsist.matchers;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArgumentsTest {
    private String stringValue = "stringValue";
    private int intValue = 1;
    private Object nullValue = null;

    @Test
    public void matchesEmptyArguments() {
       assertTrue(new Arguments(emptyList()).matches());
    }

    @Test
    public void matchesListOfArguments() {
        List<ArgumentMatcher<?>> arguments = Arrays.asList(
                new EqualsMatcher<>(intValue),
                new EqualsMatcher<>(stringValue),
                new EqualsMatcher<>(nullValue)
        );

        assertTrue(new Arguments(arguments).matches(1, stringValue, nullValue));
    }

    @Test
    public void failsWrongOrderArgumentsMatch() {
        List<ArgumentMatcher<?>> arguments = Arrays.asList(
                new EqualsMatcher<>(intValue),
                new EqualsMatcher<>(stringValue),
                new EqualsMatcher<>(nullValue)
        );

        assertFalse(new Arguments(arguments).matches(1, nullValue, stringValue));
    }
}
