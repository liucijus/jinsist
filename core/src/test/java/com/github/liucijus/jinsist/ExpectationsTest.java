package com.github.liucijus.jinsist;

import com.github.liucijus.jinsist.testtypes.Collaborator;
import org.junit.Assert;
import org.junit.Test;

public class ExpectationsTest {
    @Test
    public void stubsMethod() {
        Mockery mockery = new Mockery();

        Collaborator collaborator = mockery.mock(Collaborator.class);

        mockery.expect(collaborator).stub(mock -> mock.aMethod("input")).returns("output");

        Assert.assertEquals("output", collaborator.aMethod("input"));

        mockery.verify();
    }
}
