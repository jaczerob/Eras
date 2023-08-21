package dev.jaczerob.eras.server.toonstats.services;

import dev.jaczerob.eras.server.toonstats.models.ToonHQToon;
import dev.jaczerob.eras.server.toonstats.repositories.ToonRepository;
import dev.jaczerob.eras.server.toonstats.repositories.entities.ToonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class ToonMonitoringService {
    private final ToonScrapingService toonScrapingService;
    private final ToonRepository toonRepository;

    private static final Map<String, Integer> ORGANIC_LOOKUP = Map.of(
            "toonup", 1,
            "trap", 2,
            "lure", 3,
            "sound", 4,
            "throw", 5,
            "squirt", 6,
            "drop", 7
    );

    @Scheduled(cron = "0 */15 * * * *")
    public void monitorToons() {
        log.info("Monitoring toons...");

        final List<ToonHQToon> toons = toonScrapingService.findToons();
        final List<ToonEntity> toonEntities = toons.stream()
                .map(toonHQToon -> {
                    final ToonEntity toonEntity = new ToonEntity();

                    toonEntity.setId(toonHQToon.getId());
                    toonEntity.setSpecies(toonHQToon.getSpecies());
                    toonEntity.setLaff(toonHQToon.getLaff());
                    toonEntity.setGagToonup(toonHQToon.getToonup());
                    toonEntity.setGagTrap(toonHQToon.getTrap());
                    toonEntity.setGagLure(toonHQToon.getLure());
                    toonEntity.setGagSound(toonHQToon.getSound());
                    toonEntity.setGagThrow(toonHQToon.getThrowGag());
                    toonEntity.setGagSquirt(toonHQToon.getSquirt());
                    toonEntity.setGagDrop(toonHQToon.getDrop());
                    toonEntity.setSellbot(toonHQToon.getSellbot());
                    toonEntity.setCashbot(toonHQToon.getCashbot());
                    toonEntity.setLawbot(toonHQToon.getLawbot());
                    toonEntity.setBossbot(toonHQToon.getBossbot());

                    if (toonHQToon.getPrestiges().isEmpty()) {
                        toonEntity.setOrganic(0);
                    } else {
                        toonEntity.setOrganic(ORGANIC_LOOKUP.getOrDefault(toonHQToon.getPrestiges().get(0), 0));
                    }

                    return toonEntity;
                })
                .toList();

        log.info("Saving toons...");
        this.toonRepository.saveAll(toonEntities);
    }
}
