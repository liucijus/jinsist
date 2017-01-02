package com.github.liucijus.jinsist.report;

import com.github.liucijus.jinsist.testtypes.Collaborator;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class FormattedMethodTest {
    @Test
    public void test() throws Exception {
        Class<?> type = Collaborator.class;
        Method method = type.getMethod("firstMethod", String.class);
        List<?> arguments = Collections.singletonList("param");

        FormattedMethod formatted = new FormattedMethod(type, method, arguments);

        String expected = "Collaborator.firstMethod(param)";

        Assert.assertEquals(expected, formatted.toString());
    }
}
