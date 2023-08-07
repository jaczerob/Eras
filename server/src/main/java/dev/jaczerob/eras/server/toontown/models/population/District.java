package dev.jaczerob.eras.server.toontown.models.population;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class District {
    private String name;
    private int population;
    private String status;
}
