package com.github.liucijus.jinsist.integration;

import com.github.liucijus.jinsist.Mockery;
import com.github.liucijus.jinsist.expectations.UnexpectedInvocation;
import com.github.liucijus.jinsist.expectations.UnmetExpectations;
import com.github.liucijus.jinsist.integration.testtypes.Collaborator;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.verifyException;
import static org.junit.Assert.assertEquals;

public class MockIT {
    private Mockery mockery = new Mockery();
    private Collaborator collaborator = mockery.mock(Collaborator.class);

    @Test
    public void failsUnexpectedInvocation() {
        mockery.expect(collaborator).stub(mock -> mock.firstMethod("input")).returns("output");

        verifyException(collaborator, UnexpectedInvocation.class).firstMethod("unexpected input");

        verifyException(mockery, UnmetExpectations.class).verify();
    }

    @Test()
    public void stubsMultipleMethods() {
        mockery.expect(collaborator)
                .stub(mock -> mock.firstMethod("first input"))
                .returns("first output");

        mockery.expect(collaborator)
                .stub(mock -> mock.secondMethod("first input", "second input"))
                .returns("second output");

        assertEquals("first output", collaborator.firstMethod("first input"));
        assertEquals("second output", collaborator.secondMethod("first input", "second input"));

        mockery.verify();
    }

    @Test
    public void failsForOutOfOrderInvocations() {
        mockery.expect(collaborator)
                .stub(mock -> mock.firstMethod("first input"))
                .returns("first output");

        mockery.expect(collaborator)
                .stub(mock -> mock.secondMethod("first input", "second input"))
                .returns("second output");

        verifyException(collaborator, UnexpectedInvocation.class).secondMethod("first input", "second input");

        verifyException(mockery, UnmetExpectations.class).verify();
    }

    @Test
    public void stubsMultipleMocks() {
        Collaborator collaborator1 = mockery.mock(Collaborator.class);
        Collaborator collaborator2 = mockery.mock(Collaborator.class);

        mockery.expect(collaborator1).stub(mock -> mock.firstMethod("input1")).returns("output1");
        mockery.expect(collaborator2).stub(mock -> mock.firstMethod("input2")).returns("output2");

        assertEquals("output1", collaborator1.firstMethod("input1"));
        assertEquals("output2", collaborator2.firstMethod("input2"));

        mockery.verify();
    }
}
