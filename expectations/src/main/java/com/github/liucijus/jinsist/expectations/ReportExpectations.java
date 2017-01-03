package com.github.liucijus.jinsist.expectations;

import com.github.liucijus.jinsist.matchers.Arguments;
import com.github.liucijus.jinsist.report.FormattedReport;
import com.github.liucijus.jinsist.report.NothingEvent;
import com.github.liucijus.jinsist.report.ReportEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReportExpectations implements Expectations {
    private Expectations expectations;
    private List<ReportEvent> log = new ArrayList<>();
    private List<ReportEvent> expected = new ArrayList<>();
    private ReportEvent unexpectedEvent = new NothingEvent();

    public ReportExpectations(Expectations expectations) {
        this.expectations = expectations;
    }

    @Override
    public <ReturnType, MockType> void recordStub(
            Class<MockType> classToMock, MockType instance, Method method, Arguments arguments, ReturnType result
    ) {
        expected.add(new ExpectationEvent<>(classToMock, method, arguments));
        expectations.recordStub(classToMock, instance, method, arguments, result);
    }

    @Override
    public <MockType> Object execute(
            Class<MockType> classToMock, MockType instance, Method method, Object[] arguments
    ) {

        ExecuteEvent<MockType> executeEvent = new ExecuteEvent<>(classToMock, method, arguments);
        try {
            Object result = expectations.execute(classToMock, instance, method, arguments);
            // todo handle index out of bound
            expected.remove(0);
            log.add(executeEvent);
            return result;
        } catch (UnexpectedInvocation e) {
            unexpectedEvent = executeEvent;
            FormattedReport report = prepareReport();
            throw new UnexpectedInvocation(report.format(), e);
        }
    }

    @Override
    public void verify() {
        try {
            expectations.verify();
        } catch (UnmetExpectations e) {
            FormattedReport report = prepareReport();
            throw new UnmetExpectations(report.format(), e);
        }
    }

    private FormattedReport prepareReport() {
        ReportEvent firstOrNothing = expected.stream().findFirst().orElse(unexpectedEvent);
        return new FormattedReport(
                firstOrNothing,
                unexpectedEvent,
                log,
                expected
        );
    }
}
