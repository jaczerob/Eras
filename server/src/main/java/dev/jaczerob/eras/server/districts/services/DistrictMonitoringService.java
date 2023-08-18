package dev.jaczerob.eras.server.districts.services;

import dev.jaczerob.eras.server.districts.repositories.DistrictRepository;
import dev.jaczerob.eras.server.districts.repositories.InvasionRepository;
import dev.jaczerob.eras.server.districts.repositories.PopulationRepository;
import dev.jaczerob.eras.server.districts.repositories.entities.DistrictEntity;
import dev.jaczerob.eras.server.districts.repositories.entities.InvasionEntity;
import dev.jaczerob.eras.server.districts.repositories.entities.PopulationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class DistrictMonitoringService {
    private final DistrictService districtService;
    private final DistrictRepository districtRepository;
    private final InvasionRepository invasionRepository;
    private final PopulationRepository populationRepository;

    @Scheduled(cron = "0 */15 * * * *")
    public void monitorDistricts() {
        final var districts = districtService.getDistricts();

        final var populationEntity = PopulationEntity.from(districts);
        populationRepository.save(populationEntity);
        log.info("Saved population entity: {}", populationEntity);

        for (final var district : districts.getDistricts()) {
            final var districtEntity = DistrictEntity.from(district);
            districtRepository.save(districtEntity);

            final var optionalInvasionEntity = InvasionEntity.from(district);
            optionalInvasionEntity.ifPresent(invasionRepository::save);

            log.info("Saved district entity: {}", districtEntity);
        }
    }
}
