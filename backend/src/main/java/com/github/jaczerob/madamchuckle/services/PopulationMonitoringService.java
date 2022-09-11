package com.github.jaczerob.madamchuckle.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.jaczerob.madamchuckle.clients.ToontownClient;
import com.github.jaczerob.madamchuckle.models.population.Population;

@Component
public class PopulationMonitoringService {
    private static final Logger logger = LogManager.getLogger(PopulationMonitoringService.class);

    @Autowired private ToontownClient toontownClient;
    @Autowired private PopulationService populationService;

    @Scheduled(cron="0 */15 * * * *")
    public void monitorPopulation() {
        logger.info("Running population monitoring cron job");
        Population population = toontownClient.getPopulation();
        if (population.getError() != null) {
            logger.error("Error getting population: {}", population.getError());
            return;
        }

        logger.info("Saving population");
        populationService.save(population);
    }
}
