package dev.jaczerob.eras.server.models.ttr.districts;

import dev.jaczerob.eras.server.models.ttr.invasions.Invasion;

public class District {
    private final String name;
    private final int population;
    private final DistrictStatus districtStatus;
    private final Invasion invasion;

    public District(final String name, final int population, final DistrictStatus districtStatus, final Invasion invasion) {
        this.name = name;
        this.population = population;
        this.districtStatus = districtStatus;
        this.invasion = invasion;
    }

    public String getName() {
        return this.name;
    }

    public int getPopulation() {
        return this.population;
    }

    public DistrictStatus getStatus() {
        return this.districtStatus;
    }

    public Invasion getInvasion() {
        return this.invasion;
    }
}
