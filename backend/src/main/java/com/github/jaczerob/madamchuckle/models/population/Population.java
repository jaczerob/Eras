package com.github.jaczerob.madamchuckle.models.population;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jaczerob.madamchuckle.models.ToontownObject;
import com.google.common.collect.Streams;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
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

        Comparator<District> comparator = Comparator.comparing(d -> d.getPopulation());
        comparator.thenComparing(d -> d.getName());

        this.districts = Streams.zip(
            populationByDistrict.entrySet().stream(), 
            statusByDistrict.entrySet().stream(), 
            (populationEntry, statusEntry) -> {
                District district = new District(
                    populationEntry.getKey(), 
                    populationEntry.getValue(), 
                    statusEntry.getValue()
                );

                return district;
            }
        )
        .sorted(comparator.reversed())
        .collect(Collectors.toList());
    }
}
