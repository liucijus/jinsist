package com.github.liucijus.jinsist;

import com.github.liucijus.jinsist.expectations.UnexpectedInvocation;
import com.github.liucijus.jinsist.expectations.UnmetExpectations;
import com.github.liucijus.jinsist.mock.UnableToStubPrimitiveReturnType;
import com.github.liucijus.jinsist.testtypes.Collaborator;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.verifyException;

public class CallExpectationsTest {
    private Mockery mockery = new Mockery();
    private Collaborator collaborator = mockery.mock(Collaborator.class);

    @Test
    public void callsMethod() {
        mockery.expect(collaborator).call(mock -> mock.firstMethod("input"));

        collaborator.firstMethod("input");

        mockery.verify();
    }

    @Test
    public void failsUnexpectedInvocation() {
        mockery.expect(collaborator).call(mock -> mock.firstMethod("input"));

        verifyException(collaborator, UnexpectedInvocation.class).firstMethod("unexpected input");

        verifyException(mockery, UnmetExpectations.class).verify();
    }

    @Test
    public void callsVoidMethod() {
        mockery.expect(collaborator).call(Collaborator::voidMethod);

        collaborator.voidMethod();
    }

    @Test
    public void failsOnPrimitiveReturnType() {
        verifyException(
                mockery.expect(collaborator),
                UnableToStubPrimitiveReturnType.class
        ).call(Collaborator::primitiveMethod);
    }
}
