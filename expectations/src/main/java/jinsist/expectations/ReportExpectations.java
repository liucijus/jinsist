package jinsist.expectations;

import jinsist.matchers.Arguments;
import jinsist.report.FormattedReport;
import jinsist.report.ReportEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static jinsist.report.NothingEvent.nothing;

public class ReportExpectations implements Expectations {
    private Expectations expectations;
    private List<ReportEvent> log = new ArrayList<>();
    private List<ReportEvent> expected = new ArrayList<>();
    private ReportEvent unexpectedEvent = nothing();

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
            updateLog(executeEvent);
            return result;
        } catch (UnexpectedInvocation e) {
            return throwUnexpectedInvocation(executeEvent, e);
        }
    }

    private <MockType> void updateLog(ExecuteEvent<MockType> executeEvent) {
        expected.remove(0);
        log.add(executeEvent);
    }

    private <MockType> Object throwUnexpectedInvocation(ExecuteEvent<MockType> executeEvent, UnexpectedInvocation e) {
        unexpectedEvent = executeEvent;
        FormattedReport report = prepareReport();
        throw new UnexpectedInvocation(report.format(), e);
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
        ReportEvent firstOrNothing = expected.stream().findFirst().orElse(nothing());
        return new FormattedReport(
                firstOrNothing,
                unexpectedEvent,
                log,
                expected
        );
    }
}
