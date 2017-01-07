package jinsist.mock.delegators;

import jinsist.expectations.Expectations;
import jinsist.matchers.Arguments;
import jinsist.matchers.EqualsMatcher;
import jinsist.mock.SetupResult;
import jinsist.proxy.Delegator;

import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class SetupRecorder<ReturnType, MockType> implements Delegator<MockType> {

    private MockType instance;
    private ReturnType result;
    private SetupResult setupResult;
    private Expectations expectations;
    private Class<MockType> mockType;

    public SetupRecorder(
            Expectations expectations,
            Class<MockType> mockType,
            MockType instance,
            ReturnType result,
            SetupResult setupResult
    ) {
        this.expectations = expectations;
        this.mockType = mockType;
        this.instance = instance;
        this.result = result;
        this.setupResult = setupResult;
    }

    @Override
    public ReturnType handle(MockType setupInstance, Method method, Object[] args) {
        verifyReturnTypeNeedsToBeStubbed(result, method);

        Arguments arguments = makeArguments(args);

        expectations.recordStub(mockType, instance, method, arguments, result);
        setupResult.setSuccess();
        return result;
    }

    private Arguments makeArguments(Object[] args) {
        return new Arguments(
                stream(args).map(EqualsMatcher::new).collect(Collectors.toList())
        );
    }

    private void verifyReturnTypeNeedsToBeStubbed(ReturnType result, Method method) {
        Class<?> returnType = method.getReturnType();
        if (!returnType.equals(Void.TYPE) && returnType.isPrimitive() && result == null) {
            throw new UnableToStubPrimitiveReturnType();
        }
    }
}
