package com.github.jaczerob.madamchuckle.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.jaczerob.madamchuckle.toontown.loaders.population.models.Population;

@Component
public class PopulationMonitoringService {
    private static final Logger logger = LogManager.getLogger(PopulationMonitoringService.class);

    @Autowired private CacheService cache;
    @Autowired private PopulationService populationService;

    @Scheduled(cron="0 */15 * * * *")
    public void monitorPopulation() {
        logger.info("Running population monitoring cron job");
        Population population = (Population) cache.get("POPULATION");
        if (population.getError() != null) {
            logger.error("Error getting population: {}", population.getError());
            return;
        }

        logger.info("Saving population");
        populationService.save(population);
    }
}
