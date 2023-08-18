package dev.jaczerob.eras.server.districts.repositories;

import dev.jaczerob.eras.server.districts.repositories.entities.PopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopulationRepository extends JpaRepository<PopulationEntity, Integer> {
}
