package dev.jaczerob.eras.server.districts.models.api.population;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopulationDistrictResponse {
    private String name;
    private int population;
    private String status;
}
