package jinsist.proxy;

class ProxyInstanceCreationFailed extends RuntimeException {
    <T> ProxyInstanceCreationFailed(Class<T> type, Throwable e) {
        super("Cannot create proxy for " + type + ". Make sure your type is public.", e);
    }
}
