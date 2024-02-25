package dev.jaczerob.eras.server.models.ttr.status;

import dev.jaczerob.eras.server.models.ttr.ToontownObject;

public class Status extends ToontownObject {
    private boolean open;
    private String status;

    public Status() {
        this.open = false;
        this.status = "unknown";
    }

    public Status(final boolean open, final String status) {
        this.open = open;
        this.status = status;
    }

    public boolean getOpen() {
        return this.open;
    }

    public String getStatus() {
        return this.status;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
