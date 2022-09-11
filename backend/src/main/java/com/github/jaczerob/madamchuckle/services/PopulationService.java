package com.github.jaczerob.madamchuckle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jaczerob.madamchuckle.entities.DistrictPopulationEntity;
import com.github.jaczerob.madamchuckle.entities.TotalPopulationEntity;
import com.github.jaczerob.madamchuckle.models.Population;
import com.github.jaczerob.madamchuckle.repositories.DistrictPopulationRepository;
import com.github.jaczerob.madamchuckle.repositories.TotalPopulationRepository;

@Service
public class PopulationService {
    @Autowired private DistrictPopulationRepository districtPopulationRepository;
    @Autowired private TotalPopulationRepository totalPopulationRepository;

    public void save(Population population) {
        TotalPopulationEntity totalPopulationEntity = new TotalPopulationEntity(population.getTotalPopulation());
        totalPopulationRepository.save(totalPopulationEntity);
        
        List<DistrictPopulationEntity> districtPopulationEntities = new ArrayList<>();
        for (Entry<String, Integer> districtEntry : population.getPopulationByDistrict().entrySet()) {
            DistrictPopulationEntity districtPopulationEntity = new DistrictPopulationEntity(districtEntry.getKey(), districtEntry.getValue());
            districtPopulationEntities.add(districtPopulationEntity);
        }

        districtPopulationRepository.saveAll(districtPopulationEntities);
    }
}
