package jinsist.proxy;

public class ProxyInstanceCreationFailed extends RuntimeException {
    public <T> ProxyInstanceCreationFailed(Class<T> type, Throwable e) {
        super("Cannot create proxy for " + type + ". Make sure your type is public.", e);
    }
}
