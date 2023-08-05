package dev.jaczerob.madamchuckle.server.toontown.repositories;

import dev.jaczerob.madamchuckle.server.toontown.repositories.entities.TotalPopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalPopulationRepository extends JpaRepository<TotalPopulationEntity, Integer> {
}
