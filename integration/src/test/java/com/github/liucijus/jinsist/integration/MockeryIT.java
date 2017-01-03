package com.github.liucijus.jinsist.integration;

import com.github.liucijus.jinsist.Mockery;
import com.github.liucijus.jinsist.expectations.UnmetExpectations;
import com.github.liucijus.jinsist.integration.testtypes.Collaborator;
import org.junit.Test;

public class MockeryIT {
    @Test
    public void verifiesEmptyMockery() {
        Mockery mockery = new Mockery();

        mockery.verify();
    }

    @Test
    public void verifiesMockeryWithoutExpectations() {
        Mockery mockery = new Mockery();

        mockery.mock(Collaborator.class);

        mockery.verify();
    }

    @Test(expected = UnmetExpectations.class)
    public void failsVerificationOnUnmetExpectations() {
        Mockery mockery = new Mockery();

        Collaborator collaborator = mockery.mock(Collaborator.class);
        mockery.expect(collaborator).stub(mock -> mock.aMethodWithOneParam("some input")).returns("some output");

        mockery.verify();
    }

    @Test
    public void passesVerificationIfExpectationsAreMet() {
        Mockery mockery = new Mockery();

        Collaborator collaborator = mockery.mock(Collaborator.class);
        mockery.expect(collaborator).stub(mock -> mock.aMethodWithOneParam("some input")).returns("some output");

        collaborator.aMethodWithOneParam("some input");

        mockery.verify();
    }
}
