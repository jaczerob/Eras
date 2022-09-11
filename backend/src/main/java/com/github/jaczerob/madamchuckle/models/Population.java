package com.github.jaczerob.madamchuckle.models;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Population extends ToontownObject {
    private String error;
    private int lastUpdated;
    private int totalPopulation;
    private Map<String, Integer> populationByDistrict;
    private Map<String, String> statusByDistrict;
}
