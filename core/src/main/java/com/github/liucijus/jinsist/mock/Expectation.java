package com.github.liucijus.jinsist.mock;

public class Expectation {
    private Invocation invocation;
    private final Object result;

    public <ReturnType> Expectation(Invocation invocation, ReturnType result) {
        this.invocation = invocation;
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public boolean isFor(Invocation invocation) {
        return this.invocation.equals(invocation);
    }

    public Invocation getInvocation() {
        return invocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expectation that = (Expectation) o;

        if (!invocation.equals(that.invocation)) return false;
        return result != null ? result.equals(that.result) : that.result == null;
    }

    @Override
    public int hashCode() {
        int result1 = invocation.hashCode();
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
