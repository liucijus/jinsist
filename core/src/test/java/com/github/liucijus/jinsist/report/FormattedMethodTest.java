package com.github.liucijus.jinsist.report;

import com.github.liucijus.jinsist.testtypes.Collaborator;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class FormattedMethodTest {
    @Test
    public void test() throws Exception {
        Class<?> type = Collaborator.class;
        Method method = type.getMethod("firstMethod", String.class);
        Object[] arguments = new Object[]{"param"};

        FormattedMethod formatted = new FormattedMethod(type, method, arguments);

        String expected = "Collaborator.firstMethod(param)";

        Assert.assertEquals(expected, formatted.toString());
    }
}
