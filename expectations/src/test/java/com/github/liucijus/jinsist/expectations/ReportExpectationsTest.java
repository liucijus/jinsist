package com.github.liucijus.jinsist.expectations;

import com.github.liucijus.jinsist.expectations.testtypes.TestCollaborator;
import com.github.liucijus.jinsist.matchers.Arguments;
import org.junit.Test;

import java.lang.reflect.Method;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.CatchException.verifyException;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class ReportExpectationsTest {
    private Expectations orderExpectations = new OrderedExpectations();
    private TestCollaborator instance = new TestCollaborator();
    private Class<TestCollaborator> mockClass = TestCollaborator.class;
    private Method method1 = mockClass.getMethod("firstMethod");
    private Method method2 = mockClass.getMethod("secondMethod");
    private Arguments noArgumentsMatchers = new Arguments(emptyList());
    private Object[] noArguments = new Object[]{};

    public ReportExpectationsTest() throws NoSuchMethodException {
    }

    @Test
    public void succeedsVerificationWhenHasNoExpectations() {
        ReportExpectations expectations = new ReportExpectations(orderExpectations);

        expectations.verify();
    }

    @Test
    public void delegateToUnderlyingImplementation() {
        ReportExpectations expectations = new ReportExpectations(orderExpectations);

        expectations.recordStub(mockClass, instance, method1, noArgumentsMatchers, null);
        expectations.recordStub(mockClass, instance, method2, noArgumentsMatchers, null);

        expectations.execute(mockClass, instance, method1, noArguments);

        String expectedReport = "" +
                "Expected: TestCollaborator.secondMethod()\n" +
                "Actual: Nothing!\n" +
                "What happened before:\n" +
                "  TestCollaborator.firstMethod()\n" +
                "Unmet Expectations:\n" +
                "  TestCollaborator.secondMethod()\n";

        verifyException(expectations, UnmetExpectations.class).verify();
        assertEquals(expectedReport, caughtException().getMessage());

        expectations.execute(mockClass, instance, method2, noArguments);
        expectations.verify();
    }

    @Test
    public void reportUnexpectedInvocation() {
        ReportExpectations expectations = new ReportExpectations(orderExpectations);

        expectations.recordStub(mockClass, instance, method1, noArgumentsMatchers, null);

        String expectedReport = "" +
                "Expected: TestCollaborator.firstMethod()\n" +
                "Actual: TestCollaborator.secondMethod()\n" +
                "What happened before:\n" +
                "  Nothing!\n" +
                "Unmet Expectations:\n" +
                "  TestCollaborator.firstMethod()\n";

        verifyException(expectations, UnexpectedInvocation.class).execute(mockClass, instance, method2, noArguments);
        assertEquals(expectedReport, caughtException().getMessage());


        verifyException(expectations, UnmetExpectations.class).verify();
        assertEquals(expectedReport, caughtException().getMessage());
    }
}
