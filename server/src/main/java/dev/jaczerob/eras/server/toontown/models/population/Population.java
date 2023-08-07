package dev.jaczerob.eras.server.toontown.models.population;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Streams;
import dev.jaczerob.eras.server.toontown.models.ToontownObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class Population extends ToontownObject {
    private String error;
    private int lastUpdated;
    private int totalPopulation;
    private List<District> districts;

    public Population(
            @JsonProperty("error") String error,
            @JsonProperty("lastUpdated") int lastUpdated,
            @JsonProperty("totalPopulation") int totalPopulation,
            @JsonProperty("populationByDistrict") Map<String, Integer> populationByDistrict,
            @JsonProperty("statusByDistrict") Map<String, String> statusByDistrict
    ) {
        this.error = error;
        this.lastUpdated = lastUpdated;
        this.totalPopulation = totalPopulation;

        final var comparator = Comparator.comparing(District::getPopulation)
                .thenComparing(District::getName);

        this.districts = Streams.zip(
                        populationByDistrict.entrySet().stream(),
                        statusByDistrict.entrySet().stream(),
                        (populationEntry, statusEntry) -> new District(
                                populationEntry.getKey(),
                                populationEntry.getValue(),
                                statusEntry.getValue()
                        )
                )
                .sorted(comparator.reversed())
                .toList();
    }
}
