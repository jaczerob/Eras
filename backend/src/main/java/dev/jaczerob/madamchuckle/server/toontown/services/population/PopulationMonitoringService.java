package dev.jaczerob.madamchuckle.server.toontown.services.population;

import dev.jaczerob.madamchuckle.server.toontown.models.population.Population;
import dev.jaczerob.madamchuckle.server.toontown.services.cache.CacheKey;
import dev.jaczerob.madamchuckle.server.toontown.services.cache.CacheService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PopulationMonitoringService {
    private static final Logger logger = LogManager.getLogger(PopulationMonitoringService.class);

    private final CacheService cache;
    private final PopulationService populationService;

    public PopulationMonitoringService(
            final CacheService cache,
            final PopulationService populationService
    ) {
        this.cache = cache;
        this.populationService = populationService;
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void monitorPopulation() {
        logger.info("Running population monitoring cron job");
        final Population population = (Population) cache.get(CacheKey.Population);
        if (population.getError() != null) {
            logger.error("Error getting population: {}", population.getError());
            return;
        }

        populationService.save(population);
        logger.info("Saved population");
    }
}
