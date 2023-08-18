package dev.jaczerob.eras.server.districts.repositories;

import dev.jaczerob.eras.server.districts.repositories.entities.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Integer> {
}
