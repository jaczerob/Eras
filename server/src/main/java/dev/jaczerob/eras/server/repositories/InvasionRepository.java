package dev.jaczerob.eras.server.repositories;

import dev.jaczerob.eras.server.models.entities.InvasionEntity;
import org.springframework.data.repository.CrudRepository;

public interface InvasionRepository extends CrudRepository<InvasionEntity, Integer> {
}
