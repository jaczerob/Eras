package dev.jaczerob.eras.server.repositories;

import dev.jaczerob.eras.server.models.entities.PopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PopulationRepository extends CrudRepository<PopulationEntity, Integer> {
}
