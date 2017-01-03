package com.github.liucijus.jinsist.mock;

class SetupResult {
    private boolean wasRecorded = false;

    void setSuccess() {
        this.wasRecorded = true;
    }

    void verify() {
        if (!wasRecorded) throw new MethodToStubNotFound();
    }
}
