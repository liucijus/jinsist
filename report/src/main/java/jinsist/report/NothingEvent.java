package jinsist.report;

public class NothingEvent implements ReportEvent {
    private static NothingEvent instance = new NothingEvent();

    private NothingEvent() {
    }

    public static NothingEvent nothing() {
        return instance;
    }

    @Override
    public String toString() {
        return "Nothing!";
    }
}
