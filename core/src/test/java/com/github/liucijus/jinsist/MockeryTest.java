package com.github.liucijus.jinsist;

import org.junit.Test;

public class MockeryTest {
    @Test
    public void verifiesEmptyMockery() {
        Mockery mockery = new Mockery();

        mockery.verify();
    }
}
