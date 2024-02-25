package dev.jaczerob.eras.server.repositories;

import dev.jaczerob.eras.server.models.entities.ToonEntity;
import org.springframework.data.repository.CrudRepository;

public interface ToonRepository extends CrudRepository<ToonEntity, Integer> {
}
