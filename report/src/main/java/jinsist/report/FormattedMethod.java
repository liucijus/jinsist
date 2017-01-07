package jinsist.report;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class FormattedMethod {
    private final Class<?> type;
    private final Method method;
    private final List<?> arguments;

    public FormattedMethod(Class<?> type, Method method, List<?> arguments) {
        this.type = type;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return String.format("%s.%s(%s)", type.getSimpleName(), method.getName(), formatArguments(arguments));
    }

    private String formatArguments(List<?> arguments) {
        return arguments.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
