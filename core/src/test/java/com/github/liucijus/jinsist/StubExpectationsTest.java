package com.github.liucijus.jinsist;

import com.github.liucijus.jinsist.expectations.UnexpectedInvocation;
import com.github.liucijus.jinsist.testtypes.Collaborator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.verifyException;

public class StubExpectationsTest {
    private Mockery mockery = new Mockery();
    private Collaborator collaborator = mockery.mock(Collaborator.class);

    @Test
    public void stubsMethod() {
        mockery.expect(collaborator).stub(mock -> mock.firstMethod("input")).returns("output");

        Assert.assertEquals("output", collaborator.firstMethod("input"));
    }

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

        Assert.assertEquals("first output", collaborator.firstMethod("first input"));
        Assert.assertEquals("second output", collaborator.secondMethod("first input", "second input"));

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

        Assert.assertEquals("output1", collaborator1.firstMethod("input1"));
        Assert.assertEquals("output2", collaborator2.firstMethod("input2"));

        mockery.verify();
    }

    @Test
    @Ignore("Field stubbing not supported")
    public void allowsPublicPropertyStubbing() {
       mockery.expect(collaborator).stub(mock -> mock.publicProperty).returns("stubbed property");

        Assert.assertEquals("stubbed property", collaborator.publicProperty);

        mockery.verify();
    }
}
