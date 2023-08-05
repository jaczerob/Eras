package dev.jaczerob.madamchuckle.toontown.repositories;

import dev.jaczerob.madamchuckle.toontown.repositories.entities.DistrictPopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictPopulationRepository extends JpaRepository<DistrictPopulationEntity, Integer> {
}
