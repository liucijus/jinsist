package jinsist.integration.testtypes;

public class Collaborator {
    public String publicProperty = "property value";

    public String firstMethod(String param) {
        throw new UnsupportedOperationException("firstMethod was called from implementation.");
    }

    public String secondMethod(String param, String param2) {
        throw new UnsupportedOperationException("secondMethod was called from implementation.");
    }

    public void voidMethod() {
    }

    public int primitiveMethod() {
        return 0;
    }
}
