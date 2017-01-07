package jinsist.expectations;

import jinsist.report.FormattedMethod;
import jinsist.report.ReportEvent;

import java.lang.reflect.Method;

import static java.util.Arrays.asList;

public class ExecuteEvent<MockType> implements ReportEvent {
    private final Class<MockType> mockClass;
    private final Method method;
    private final Object[] arguments;

    ExecuteEvent(Class<MockType> mockClass, Method method, Object[] arguments) {
        this.mockClass = mockClass;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return new FormattedMethod(mockClass, method, asList(arguments)).toString();
    }
}
