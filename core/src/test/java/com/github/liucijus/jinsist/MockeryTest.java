package com.github.liucijus.jinsist;

import org.junit.Test;

public class MockeryTest {
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

        collaborator.aMethodWithOneParam("input");

        mockery.verify();
    }
 }
