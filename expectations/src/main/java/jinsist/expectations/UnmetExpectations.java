package jinsist.expectations;

public class UnmetExpectations extends RuntimeException {
    UnmetExpectations() {
    }

    UnmetExpectations(String message, Throwable throwable) {
        super(message, throwable);
    }
}
