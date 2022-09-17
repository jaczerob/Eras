package com.github.jaczerob.madamchuckle.toontown.loaders.population.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class District {
    private String name;
    private int population;
    private String status;
}
