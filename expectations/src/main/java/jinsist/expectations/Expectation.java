package jinsist.expectations;

public class Expectation<MockType, ReturnType> {
    private ExpectedInvocation<MockType> expectedInvocation;
    private final ReturnType result;

    Expectation(ExpectedInvocation<MockType> expectedInvocation, ReturnType result) {
        this.expectedInvocation = expectedInvocation;
        this.result = result;
    }

    Object getResult() {
        return result;
    }

    boolean isFor(Invocation invocation) {
        return this.expectedInvocation.getInstance().equals(invocation.getInstance())
                && this.expectedInvocation.getMethod().equals(invocation.getMethod())
                && this.expectedInvocation.getArguments().matches(invocation.getArguments());
    }

    ExpectedInvocation getExpectedInvocation() {
        return expectedInvocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expectation that = (Expectation) o;

        return expectedInvocation.equals(that.expectedInvocation)
                && (result != null ? result.equals(that.result) : that.result == null);
    }

    @Override
    public int hashCode() {
        int result1 = expectedInvocation.hashCode();
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
