package dev.jaczerob.eras.server.utils;

import org.springframework.lang.Nullable;

public class ToontownAPIObject {
    @Nullable
    private final String error;

    private final int lastUpdated;

    public ToontownAPIObject(@Nullable final String error, final int lastUpdated) {
        this.error = error;
        this.lastUpdated = lastUpdated;
    }

    @Nullable
    public String getError() {
        return this.error;
    }

    public int getLastUpdated() {
        return this.lastUpdated;
    }
}
