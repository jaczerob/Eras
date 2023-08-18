package dev.jaczerob.eras.server.districts.models.api.population;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Streams;
import dev.jaczerob.eras.server.utils.ToontownAPIObject;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
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
}
