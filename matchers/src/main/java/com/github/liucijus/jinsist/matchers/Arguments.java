package com.github.liucijus.jinsist.matchers;

import java.util.List;

public class Arguments {
    private List<ArgumentMatcher<?>> argumentMatchers;

    public Arguments(List<ArgumentMatcher<?>> argumentMatchers) {
        this.argumentMatchers = argumentMatchers;
    }

    public boolean matches(Object... arguments) {
        int length = argumentMatchers.size();
        if (length != arguments.length)
            return false;
        else
            for (int i = 0; i < length; i++) {
                ArgumentMatcher matcher = argumentMatchers.get(i);
                @SuppressWarnings("unchecked")
                boolean matches = matcher.matches(arguments[i]);
                if (!matches) return false;
            }
        return true;
    }

    public List<ArgumentMatcher<?>> getArgumentMatchers() {
        return argumentMatchers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arguments arguments = (Arguments) o;

        return argumentMatchers.equals(arguments.argumentMatchers);
    }

    @Override
    public int hashCode() {
        return argumentMatchers != null ? argumentMatchers.hashCode() : 0;
    }
}
