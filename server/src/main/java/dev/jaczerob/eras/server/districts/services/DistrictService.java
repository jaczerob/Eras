package dev.jaczerob.eras.server.districts.services;

import dev.jaczerob.eras.server.districts.models.api.invasions.InvadedDistrictResponse;
import dev.jaczerob.eras.server.districts.models.api.population.PopulationDistrictResponse;
import dev.jaczerob.eras.server.districts.models.eras.District;
import dev.jaczerob.eras.server.districts.models.eras.Districts;
import dev.jaczerob.eras.server.districts.models.eras.Invasion;
import dev.jaczerob.eras.server.districts.models.eras.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DistrictService {
    private final DistrictAPIService districtAPIService;

    public DistrictService(final DistrictAPIService districtAPIService) {
        this.districtAPIService = districtAPIService;
    }

    public Districts getDistricts() {
        final var populationResponse = districtAPIService.getPopulation();
        final var invasionResponse = districtAPIService.getInvasions();

        final int totalPopulation = populationResponse.getTotalPopulation();

        final Map<String, PopulationDistrictResponse> districts = populationResponse.getDistricts();
        final Map<String, InvadedDistrictResponse> invadedDistricts = invasionResponse.getInvadedDistricts();

        final List<District> districtList = new ArrayList<>();

        districts.forEach((district, districtData) -> {
            final var population = districtData.getPopulation();
            final var status = Status.valueOf(districtData.getStatus().toUpperCase());

            final Invasion invasion;
            if (invadedDistricts.containsKey(district)) {
                final var invasionData = invadedDistricts.get(district);
                final var cog = invasionData.getType();
                final var cogsDefeated = invasionData.getCogsDefeated();
                final var totalCogs = invasionData.getTotalCogs();
                invasion = new Invasion(cogsDefeated, totalCogs, cog);
            } else {
                invasion = null;
            }

            districtList.add(new District(district, population, status, invasion));
        });

        return new Districts(totalPopulation, districtList);
    }
}
