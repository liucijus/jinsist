package jinsist.mock;

public class SetupResult {
    private boolean wasRecorded = false;

    public void setSuccess() {
        this.wasRecorded = true;
    }

    void verify() {
        if (!wasRecorded) throw new MethodToStubNotFound();
    }
}
