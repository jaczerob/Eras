package dev.jaczerob.eras.server.services;

import dev.jaczerob.eras.server.models.ttr.districts.District;
import dev.jaczerob.eras.server.models.ttr.districts.DistrictStatus;
import dev.jaczerob.eras.server.models.ttr.districts.Districts;
import dev.jaczerob.eras.server.models.ttr.invasions.InvadedDistrictResponse;
import dev.jaczerob.eras.server.models.ttr.invasions.Invasion;
import dev.jaczerob.eras.server.models.ttr.invasions.InvasionResponse;
import dev.jaczerob.eras.server.models.ttr.population.PopulationDistrictResponse;
import dev.jaczerob.eras.server.models.ttr.population.PopulationResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DistrictService {
    private final ToontownAPI toontownAPI;

    public DistrictService(final ToontownAPI toontownAPI) {
        this.toontownAPI = toontownAPI;
    }

    public Districts getDistricts() {
        final PopulationResponse populationResponse = toontownAPI.getPopulation();
        final InvasionResponse invasionResponse = toontownAPI.getInvasions();

        final int totalPopulation = populationResponse.getTotalPopulation();

        final Map<String, PopulationDistrictResponse> districts = populationResponse.getDistricts();
        final Map<String, InvadedDistrictResponse> invadedDistricts = invasionResponse.getInvadedDistricts();

        final List<District> districtList = new ArrayList<>();

        districts.forEach((district, districtData) -> {
            final var population = districtData.getPopulation();
            final var status = DistrictStatus.valueOf(districtData.getStatus().toUpperCase());

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
