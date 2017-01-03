package com.github.liucijus.mock;

import com.github.liucijus.jinsist.mock.MethodToStubNotFound;
import com.github.liucijus.jinsist.mock.Mock;
import com.github.liucijus.jinsist.mock.UnableToStubPrimitiveReturnType;
import com.github.liucijus.mock.testtypes.Collaborator;
import com.github.liucijus.mock.testtypes.TestFactories;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockCallTest extends TestFactories {
    @Test
    public void mocksMethodWithVoidReturnType() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.call(Collaborator::voidMethod);

        assertEquals(stub(Collaborator.class, voidMethod), registry.get(0));
    }

    @Test
    public void mocksMethodWithReturnType() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.call(mock -> mock.firstMethod(input));

        assertEquals(stub(Collaborator.class, firstMethod, argumentsOf(input)), registry.get(0));
    }

    @Test(expected = UnableToStubPrimitiveReturnType.class)
    public void failsOnPrimitiveReturnType() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.call(Collaborator::primitiveMethod);
    }

    @Test(expected = MethodToStubNotFound.class)
    public void failsOnMissingMethodToCall() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);
        collaboratorMock.call(mock -> {
        });
    }

    public MockCallTest() throws NoSuchMethodException {
    }
}
