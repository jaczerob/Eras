package dev.jaczerob.eras.server.districts.repositories;

import dev.jaczerob.eras.server.districts.repositories.entities.InvasionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvasionRepository extends JpaRepository<InvasionEntity, Integer> {
}
