package dev.jaczerob.madamchuckle.toontown.repositories;

import dev.jaczerob.madamchuckle.toontown.repositories.entities.TotalPopulationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalPopulationRepository extends JpaRepository<TotalPopulationEntity, Integer> {
}
