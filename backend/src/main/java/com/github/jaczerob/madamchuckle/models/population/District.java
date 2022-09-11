package com.github.jaczerob.madamchuckle.models.population;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class District {
    private String name;
    private int population;
    private String status;
}
