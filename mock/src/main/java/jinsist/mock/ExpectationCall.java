package jinsist.mock;

public interface ExpectationCall<MockType> {
    void recordStub(MockType mock);
}
