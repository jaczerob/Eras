package dev.jaczerob.eras.server.models.ttr.population;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Streams;
import dev.jaczerob.eras.server.models.ttr.ToontownAPIObject;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PopulationResponse extends ToontownAPIObject {
    private final int totalPopulation;
    private final Map<String, PopulationDistrictResponse> districts;

    public PopulationResponse(
        @JsonProperty("error") final String error,
        @JsonProperty("lastUpdated") final int lastUpdated,
        @JsonProperty("totalPopulation") final int totalPopulation,
        @JsonProperty("populationByDistrict") final Map<String, Integer> populationByDistrict,
        @JsonProperty("statusByDistrict") final Map<String, String> statusByDistrict
    ) {
        super(error, lastUpdated);
        this.totalPopulation = totalPopulation;

        this.districts = Streams.zip(
            populationByDistrict.entrySet().stream(),
            statusByDistrict.entrySet().stream(),
            (populationEntry, statusEntry) -> new PopulationDistrictResponse(
                    populationEntry.getKey(),
                    populationEntry.getValue(),
                    statusEntry.getValue()
            )
        )
        .collect(Collectors.toMap(PopulationDistrictResponse::getName, Function.identity()));
    }

    public int getTotalPopulation() {
        return this.totalPopulation;
    }

    public Map<String, PopulationDistrictResponse> getDistricts() {
        return this.districts;
    }
}
