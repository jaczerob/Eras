package dev.jaczerob.eras.server.districts.models.eras;

public enum Status {
    ONLINE("online"),
    OFFLINE("offline"),
    DRAINING("draining"),
    CLOSED("closed");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
