package com.github.jaczerob.madamchuckle.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jaczerob.madamchuckle.entities.DistrictPopulationEntity;
import com.github.jaczerob.madamchuckle.entities.TotalPopulationEntity;
import com.github.jaczerob.madamchuckle.repositories.DistrictPopulationRepository;
import com.github.jaczerob.madamchuckle.repositories.TotalPopulationRepository;
import com.github.jaczerob.madamchuckle.toontown.loaders.population.models.District;
import com.github.jaczerob.madamchuckle.toontown.loaders.population.models.Population;

@Service
public class PopulationService {
    @Autowired private DistrictPopulationRepository districtPopulationRepository;
    @Autowired private TotalPopulationRepository totalPopulationRepository;

    public void save(Population population) {
        TotalPopulationEntity totalPopulationEntity = new TotalPopulationEntity(population.getTotalPopulation());
        totalPopulationRepository.save(totalPopulationEntity);
        
        List<DistrictPopulationEntity> districtPopulationEntities = new ArrayList<>();
        for (District district : population.getDistricts()) {
            DistrictPopulationEntity districtPopulationEntity = new DistrictPopulationEntity(district.getName(), district.getPopulation());
            districtPopulationEntities.add(districtPopulationEntity);
        }

        districtPopulationRepository.saveAll(districtPopulationEntities);
    }
}
