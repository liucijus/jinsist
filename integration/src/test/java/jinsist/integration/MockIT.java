package jinsist.integration;

import jinsist.Mockery;
import jinsist.expectations.UnexpectedInvocation;
import jinsist.expectations.UnmetExpectations;
import jinsist.integration.testtypes.Collaborator;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.verifyException;
import static org.junit.Assert.assertEquals;

public class MockIT {
    private Mockery mockery = new Mockery();
    private Collaborator collaborator = mockery.mock(Collaborator.class);

    @Test
    public void failsUnexpectedInvocation() {
        mockery.expect(collaborator).query(mock -> mock.firstMethod("input")).returns("output");

        verifyException(collaborator, UnexpectedInvocation.class).firstMethod("unexpected input");

        verifyException(mockery, UnmetExpectations.class).verify();
    }

    @Test()
    public void stubsMultipleMethods() {
        mockery.expect(collaborator)
                .query(mock -> mock.firstMethod("first input"))
                .returns("first output");

        mockery.expect(collaborator)
                .query(mock -> mock.secondMethod("first input", "second input"))
                .returns("second output");

        assertEquals("first output", collaborator.firstMethod("first input"));
        assertEquals("second output", collaborator.secondMethod("first input", "second input"));

        mockery.verify();
    }

    @Test
    public void failsForOutOfOrderInvocations() {
        mockery.expect(collaborator)
                .query(mock -> mock.firstMethod("first input"))
                .returns("first output");

        mockery.expect(collaborator)
                .query(mock -> mock.secondMethod("first input", "second input"))
                .returns("second output");

        verifyException(collaborator, UnexpectedInvocation.class).secondMethod("first input", "second input");

        verifyException(mockery, UnmetExpectations.class).verify();
    }

    @Test
    public void stubsMultipleMocks() {
        Collaborator collaborator1 = mockery.mock(Collaborator.class);
        Collaborator collaborator2 = mockery.mock(Collaborator.class);

        mockery.expect(collaborator1).query(mock -> mock.firstMethod("input1")).returns("output1");
        mockery.expect(collaborator2).query(mock -> mock.firstMethod("input2")).returns("output2");

        assertEquals("output1", collaborator1.firstMethod("input1"));
        assertEquals("output2", collaborator2.firstMethod("input2"));

        mockery.verify();
    }
}
