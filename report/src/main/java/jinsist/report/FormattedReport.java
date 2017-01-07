package jinsist.report;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static jinsist.report.NothingEvent.nothing;

public class FormattedReport {
    private static final String INDENTATION = "  ";
    private final ReportEvent expected;
    private final ReportEvent actual;
    private final List<ReportEvent> history;
    private final List<ReportEvent> unmetHistory;

    public FormattedReport(
            ReportEvent expected,
            ReportEvent actual,
            List<ReportEvent> history,
            List<ReportEvent> unmetHistory
    ) {
        this.expected = expected;
        this.actual = actual;
        this.history = history;
        this.unmetHistory = unmetHistory;
    }

    public String format() {
        return "Expected: " + expected.toString() + "\n" +
                "Actual: " + actual.toString() + "\n" +
                "What happened before:\n" +
                formatEventList(history) + "\n" +
                "Unmet Expectations:\n" +
                formatEventList(unmetHistory) + "\n";
    }

    private String indented(ReportEvent event) {
        return INDENTATION + event.toString();
    }

    private String formatEventList(List<ReportEvent> history) {
        return history.isEmpty()
                ? indented(nothing())
                : history.stream().map(this::indented).collect(joining("\n"));
    }
}
