package dev.jaczerob.eras.server.toontown.services.population;

import dev.jaczerob.eras.server.toontown.models.population.District;
import dev.jaczerob.eras.server.toontown.models.population.Population;
import dev.jaczerob.eras.server.toontown.repositories.DistrictPopulationRepository;
import dev.jaczerob.eras.server.toontown.repositories.TotalPopulationRepository;
import dev.jaczerob.eras.server.toontown.repositories.entities.DistrictPopulationEntity;
import dev.jaczerob.eras.server.toontown.repositories.entities.TotalPopulationEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopulationService {
    private final DistrictPopulationRepository districtPopulationRepository;
    private final TotalPopulationRepository totalPopulationRepository;

    public PopulationService(
            final DistrictPopulationRepository districtPopulationRepository,
            final TotalPopulationRepository totalPopulationRepository
    ) {
        this.districtPopulationRepository = districtPopulationRepository;
        this.totalPopulationRepository = totalPopulationRepository;
    }

    public void save(final Population population) {
        final TotalPopulationEntity totalPopulationEntity = new TotalPopulationEntity(population.getTotalPopulation());
        totalPopulationRepository.save(totalPopulationEntity);

        final List<DistrictPopulationEntity> districtPopulationEntities = new ArrayList<>();
        for (final District district : population.getDistricts()) {
            final DistrictPopulationEntity districtPopulationEntity = new DistrictPopulationEntity(district.getName(), district.getPopulation());
            districtPopulationEntities.add(districtPopulationEntity);
        }

        districtPopulationRepository.saveAll(districtPopulationEntities);
    }
}
