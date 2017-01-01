package com.github.liucijus.jinsist;

import com.github.liucijus.jinsist.mock.UnexpectedInvocation;
import com.github.liucijus.jinsist.testtypes.Collaborator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class ExpectationsTest {
    private Mockery mockery = new Mockery();
    private Collaborator collaborator = mockery.mock(Collaborator.class);

    @Test
    public void stubsMethod() {
        mockery.expect(collaborator).stub(mock -> mock.aMethod("input")).returns("output");

        Assert.assertEquals("output", collaborator.aMethod("input"));
    }

    @Test(expected = UnexpectedInvocation.class)
    public void failsUnexpectedInvocation() {
        mockery.expect(collaborator).stub(mock -> mock.aMethod("input")).returns("output");

        Assert.assertEquals("output", collaborator.aMethod("unexpected input"));
    }

    @After
    public void verify() {
        mockery.verify();
    }
}
