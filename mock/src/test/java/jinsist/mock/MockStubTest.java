package jinsist.mock;

import jinsist.mock.testtypes.Collaborator;
import jinsist.mock.testtypes.TestFactories;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockStubTest extends TestFactories {
    @Test
    public void stubsMethod() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.stub(mock -> mock.firstMethod("input")).returns("output");

        assertEquals(stub(Collaborator.class, firstMethod, argumentsOf("input"), "output"), registry.get(0));
    }

    @Test(expected = MethodToStubNotFound.class)
    public void failsOnPublicPropertyStubbing() {
        Mock<Collaborator> collaboratorMock = new Mock<>(Collaborator.class, expectations);

        collaboratorMock.stub(mock -> mock.publicProperty).returns("ignored");
    }

    public MockStubTest() throws NoSuchMethodException {
    }
}
