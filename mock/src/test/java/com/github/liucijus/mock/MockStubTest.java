package com.github.liucijus.mock;

import com.github.liucijus.jinsist.mock.MethodToStubNotFound;
import com.github.liucijus.jinsist.mock.Mock;
import com.github.liucijus.mock.testtypes.Collaborator;
import com.github.liucijus.mock.testtypes.TestFactories;
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
