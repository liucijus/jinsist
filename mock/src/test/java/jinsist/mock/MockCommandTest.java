package jinsist.mock;

import jinsist.mock.delegators.UnableToStubPrimitiveReturnType;
import jinsist.mock.testtypes.Collaborator;
import jinsist.mock.testtypes.TestFactories;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockCommandTest extends TestFactories {
    @Test
    public void mocksMethodWithVoidReturnType() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.command(Collaborator::voidMethod);

        assertEquals(stub(Collaborator.class, voidMethod), registry.get(0));
    }

    @Test
    public void mocksMethodWithReturnType() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.command(mock -> mock.firstMethod(input));

        assertEquals(stub(Collaborator.class, firstMethod, argumentsOf(input)), registry.get(0));
    }

    @Test(expected = UnableToStubPrimitiveReturnType.class)
    public void failsOnPrimitiveReturnType() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.command(Collaborator::primitiveMethod);
    }

    @Test(expected = MethodToStubNotFound.class)
    public void failsOnMissingMethodToCall() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);
        collaboratorMock.command(mock -> {
        });
    }

    public MockCommandTest() throws NoSuchMethodException {
    }
}
