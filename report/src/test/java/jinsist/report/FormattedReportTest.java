package jinsist.report;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FormattedReportTest {
    @Test
    public void formatsExpectationReport() {
        ReportEvent expected = anEvent("expected invocation");
        ReportEvent actual = anEvent("actual invocation");
        List<ReportEvent> history = aHistory("event 1", "event 2", "event 3");
        List<ReportEvent> unmetHistory = aHistory("event 4", "event 5", "event 6");

        FormattedReport report = new FormattedReport(
                expected,
                actual,
                history,
                unmetHistory
        );

        String expectedReport = "" +
                "Expected: expected invocation\n" +
                "Actual: actual invocation\n" +
                "What happened before:\n" +
                "  event 1\n" +
                "  event 2\n" +
                "  event 3\n" +
                "Unmet Expectations:\n" +
                "  event 4\n" +
                "  event 5\n" +
                "  event 6\n";

        Assert.assertEquals(expectedReport, report.format());
    }

    @Test
    public void formatsEmptyHistoryAsNothing() {
        ReportEvent expected = anEvent("expected invocation");
        ReportEvent actual = anEvent("actual invocation");
        List<ReportEvent> emptyHistory = aHistory();
        List<ReportEvent> emptyUnmetHistory = aHistory();

        FormattedReport report = new FormattedReport(
                expected,
                actual,
                emptyHistory,
                emptyUnmetHistory
        );

        String expectedReport = "" +
                "Expected: expected invocation\n" +
                "Actual: actual invocation\n" +
                "What happened before:\n" +
                "  Nothing!\n" +
                "Unmet Expectations:\n" +
                "  Nothing!\n";

        Assert.assertEquals(expectedReport, report.format());
    }

    class TestReportEvent implements ReportEvent {
        private String text;

        public TestReportEvent(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    private ReportEvent anEvent(String text) {
        return new TestReportEvent(text);
    }

    private List<ReportEvent> aHistory(String... events) {
        return Arrays.stream(events).map(TestReportEvent::new).collect(Collectors.toList());
    }
}
