package dev.jaczerob.eras.server.models.ttr.invasions;

public class InvadedDistrictResponse {
    private final String district;
    private final int asOf;
    private final String type;
    private final int cogsDefeated;
    private final int totalCogs;

    public InvadedDistrictResponse(final String district, final int asOf, final String type, final int cogsDefeated, final int totalCogs) {
        this.district = district;
        this.asOf = asOf;
        this.type = type;
        this.cogsDefeated = cogsDefeated;
        this.totalCogs = totalCogs;
    }

    public String getDistrict() {
        return this.district;
    }

    public int getAsOf() {
        return this.asOf;
    }

    public String getType() {
        return this.type;
    }

    public int getCogsDefeated() {
        return this.cogsDefeated;
    }

    public int getTotalCogs() {
        return this.totalCogs;
    }
}
