package dev.jaczerob.eras.server.districts.services;

import dev.jaczerob.eras.server.districts.models.api.invasions.InvasionResponse;
import dev.jaczerob.eras.server.districts.models.api.population.PopulationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "toontown-districts", url = "https://www.toontownrewritten.com/api")
public interface DistrictAPIService {
    @RequestMapping("/invasions")
    InvasionResponse getInvasions();

    @RequestMapping("/population")
    PopulationResponse getPopulation();
}
