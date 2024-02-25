package dev.jaczerob.eras.server.controllers;

import dev.jaczerob.eras.server.models.toonstats.ToonStats;
import dev.jaczerob.eras.server.models.ttr.districts.Districts;
import dev.jaczerob.eras.server.models.ttr.fieldoffices.FieldOffices;
import dev.jaczerob.eras.server.models.ttr.news.News;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotes;
import dev.jaczerob.eras.server.models.ttr.status.Status;
import dev.jaczerob.eras.server.services.TTRAPIService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller("/graphql")
public class TTRGraphQLController {
    private final TTRAPIService ttrAPIService;

    public TTRGraphQLController(final TTRAPIService cache) {
        this.ttrAPIService = cache;
    }

    @QueryMapping
    public TTRAPIResponse pullData() {
        return new TTRAPIResponse();
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "news")
    public News getNews(final TTRAPIResponse response) {
        final Optional<News> optionalNews = this.ttrAPIService.getNews();
        return optionalNews.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "releaseNotes")
    public ReleaseNotes getReleaseNotes(final TTRAPIResponse response) {
        final Optional<ReleaseNotes> optionalReleaseNotes = this.ttrAPIService.getReleaseNotes();
        return optionalReleaseNotes.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "fieldOffices")
    public FieldOffices getFieldOffices(final TTRAPIResponse response) {
        final Optional<FieldOffices> optionalFieldOffices = this.ttrAPIService.getFieldOffices();
        return optionalFieldOffices.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "status")
    public Status getStatus(final TTRAPIResponse response) {
        final Optional<Status> optionalStatus = this.ttrAPIService.getStatus();
        return optionalStatus.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "toonStats")
    public ToonStats getToonStats(final TTRAPIResponse response) {
        final Optional<ToonStats> optionalToonStats = this.ttrAPIService.getToonStats();
        return optionalToonStats.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "districts")
    public Districts getDistricts(final TTRAPIResponse response) {
        final Optional<Districts> optionalDistricts = this.ttrAPIService.getDistricts();
        return optionalDistricts.orElse(null);
    }

}
