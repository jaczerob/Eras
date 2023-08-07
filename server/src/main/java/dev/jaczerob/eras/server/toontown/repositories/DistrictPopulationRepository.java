package dev.jaczerob.eras.server.toontown.repositories;

import dev.jaczerob.eras.server.toontown.repositories.entities.DistrictPopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictPopulationRepository extends JpaRepository<DistrictPopulationEntity, Integer> {
}
