package com.github.liucijus.jinsist.report;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class FormattedMethodTest {
    interface TestClass {
        String aMethod(String param);
    }

    @Test
    public void formatsMethodWithArguments() throws Exception {
        Class<?> type = TestClass.class;
        Method method = type.getMethod("aMethod", String.class);
        List<?> arguments = Collections.singletonList("param");

        FormattedMethod formatted = new FormattedMethod(type, method, arguments);

        String expected = "TestClass.aMethod(param)";

        Assert.assertEquals(expected, formatted.toString());
    }
}
