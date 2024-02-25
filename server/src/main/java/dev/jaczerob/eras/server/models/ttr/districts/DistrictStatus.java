package dev.jaczerob.eras.server.models.ttr.districts;

public enum DistrictStatus {
    ONLINE("online"),
    OFFLINE("offline"),
    DRAINING("draining"),
    CLOSED("closed");

    private final String value;

    DistrictStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
