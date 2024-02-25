package dev.jaczerob.eras.server.services;

import dev.jaczerob.eras.server.models.toonstats.ToonStats;
import dev.jaczerob.eras.server.models.ttr.districts.District;
import dev.jaczerob.eras.server.models.ttr.districts.DistrictStatus;
import dev.jaczerob.eras.server.models.ttr.districts.Districts;
import dev.jaczerob.eras.server.models.ttr.fieldoffices.FieldOffices;
import dev.jaczerob.eras.server.models.ttr.invasions.InvadedDistrictResponse;
import dev.jaczerob.eras.server.models.ttr.invasions.Invasion;
import dev.jaczerob.eras.server.models.ttr.invasions.InvasionResponse;
import dev.jaczerob.eras.server.models.ttr.news.News;
import dev.jaczerob.eras.server.models.ttr.population.PopulationDistrictResponse;
import dev.jaczerob.eras.server.models.ttr.population.PopulationResponse;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotes;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.eras.server.models.ttr.status.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TTRAPIService {
    private ToonStats toonStats = null;
    private Districts districts = null;
    private Status status = null;
    private FieldOffices fieldOffices = null;
    private ReleaseNotes releaseNotes = null;
    private News news = null;

    private final ToonStatsService toonStatsService;
    private final TTRAPIClient ttrAPIClient;

    public TTRAPIService(final ToonStatsService toonStatsService, final TTRAPIClient ttrAPIClient) {
        this.toonStatsService = toonStatsService;
        this.ttrAPIClient = ttrAPIClient;
    }

    public Optional<ToonStats> getToonStats() {
        return Optional.ofNullable(this.toonStats);
    }

    public Optional<Districts> getDistricts() {
        return Optional.ofNullable(this.districts);
    }

    public Optional<Status> getStatus() {
        return Optional.ofNullable(this.status);
    }

    public Optional<FieldOffices> getFieldOffices() {
        return Optional.ofNullable(this.fieldOffices);
    }

    public Optional<ReleaseNotes> getReleaseNotes() {
        return Optional.ofNullable(this.releaseNotes);
    }

    public Optional<News> getNews() {
        return Optional.ofNullable(this.news);
    }

    @Scheduled(fixedRate = 60000)
    void updateTTRData() {
        this.status = this.ttrAPIClient.getStatus();
        this.fieldOffices = this.ttrAPIClient.getFieldOffices();

        final List<ReleaseNotesPartial> releaseNotes = this.ttrAPIClient.getReleaseNotes();
        if (!releaseNotes.isEmpty()) {
            this.releaseNotes = this.ttrAPIClient.getReleaseNotes(releaseNotes.getFirst().getNoteId());
        }

        this.news = this.ttrAPIClient.getNews();
        this.toonStats = this.toonStatsService.getToonStats();
        this.districts = this.fetchDistricts();
    }

    Districts fetchDistricts() {
        final PopulationResponse populationResponse = this.ttrAPIClient.getPopulation();
        final InvasionResponse invasionResponse = this.ttrAPIClient.getInvasions();

        final int totalPopulation = populationResponse.getTotalPopulation();

        final Map<String, PopulationDistrictResponse> districts = populationResponse.getDistricts();
        final Map<String, InvadedDistrictResponse> invadedDistricts = invasionResponse.getInvadedDistricts();

        final List<District> districtList = new ArrayList<>();

        districts.forEach((district, districtData) -> {
            final var population = districtData.getPopulation();
            final var status = DistrictStatus.valueOf(districtData.getStatus().toUpperCase());

            final Invasion invasion;
            if (invadedDistricts.containsKey(district)) {
                final InvadedDistrictResponse invasionData = invadedDistricts.get(district);
                final String cog = invasionData.getType();
                final int cogsDefeated = invasionData.getCogsDefeated();
                final int totalCogs = invasionData.getTotalCogs();
                invasion = new Invasion(cogsDefeated, totalCogs, cog);
            } else {
                invasion = null;
            }

            districtList.add(new District(district, population, status, invasion));
        });

        return new Districts(totalPopulation, districtList);
    }
}
