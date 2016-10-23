package org.moita.keep.config;

import java.util.function.Supplier;

public class KeepConfiguration {

    private int flushRetryInterval = 0;

    private Supplier<Boolean> flushRule = () -> true;

    public int getFlushRetryInterval() {
        return flushRetryInterval;
    }

    public void setFlushRetryInterval(int flushInterval) {
        this.flushRetryInterval = flushInterval;
    }

    public void setFlushRules(Supplier<Boolean> flushRule) {
        this.flushRule = flushRule;
    }

    public Supplier<Boolean> isFlushAllowed() {
        return flushRule;
    }
}
