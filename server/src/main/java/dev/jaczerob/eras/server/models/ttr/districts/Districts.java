package dev.jaczerob.eras.server.models.ttr.districts;

import dev.jaczerob.eras.server.models.ttr.ToontownObject;

import java.util.List;

public class Districts extends ToontownObject {
    private final int totalPopulation;
    private final List<District> districts;

    public Districts(final int totalPopulation, final List<District> districts) {
        this.totalPopulation = totalPopulation;
        this.districts = districts;
    }

    public int getTotalPopulation() {
        return this.totalPopulation;
    }

    public List<District> getDistricts() {
        return this.districts;
    }
}
