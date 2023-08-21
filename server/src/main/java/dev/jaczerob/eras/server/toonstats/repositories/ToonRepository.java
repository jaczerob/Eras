package dev.jaczerob.eras.server.toonstats.repositories;

import dev.jaczerob.eras.server.toonstats.repositories.entities.ToonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToonRepository extends JpaRepository<ToonEntity, Integer> {
}
