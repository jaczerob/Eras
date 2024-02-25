package dev.jaczerob.eras.server.controllers;

import dev.jaczerob.eras.server.cache.CacheKey;
import dev.jaczerob.eras.server.cache.TTRAPICache;
import dev.jaczerob.eras.server.models.toonstats.ToonStats;
import dev.jaczerob.eras.server.models.ttr.districts.Districts;
import dev.jaczerob.eras.server.models.ttr.fieldoffices.FieldOffices;
import dev.jaczerob.eras.server.models.ttr.news.News;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotes;
import dev.jaczerob.eras.server.models.ttr.status.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller("/graphql")
public class TTRGraphQLController {
    private static final Logger log = LogManager.getLogger();

    private final TTRAPICache cache;

    public TTRGraphQLController(final TTRAPICache cache) {
        this.cache = cache;
    }

    @QueryMapping
    public TTRAPIResponse pullData() {
        return new TTRAPIResponse();
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "news")
    public News getNews(final TTRAPIResponse response) {
        final Optional<News> optionalNews = this.cache.get(CacheKey.News);
        return optionalNews.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "releaseNotes")
    public ReleaseNotes getReleaseNotes(final TTRAPIResponse response) {
        final Optional<ReleaseNotes> optionalReleaseNotes = this.cache.get(CacheKey.ReleaseNotes);
        return optionalReleaseNotes.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "fieldOffices")
    public FieldOffices getFieldOffices(final TTRAPIResponse response) {
        final Optional<FieldOffices> optionalFieldOffices = this.cache.get(CacheKey.FieldOffices);
        return optionalFieldOffices.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "status")
    public Status getStatus(final TTRAPIResponse response) {
        final Optional<Status> optionalStatus = this.cache.get(CacheKey.Status);
        return optionalStatus.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "toonStats")
    public ToonStats getToonStats(final TTRAPIResponse response) {
        final Optional<ToonStats> optionalToonStats = this.cache.get(CacheKey.ToonStats);
        return optionalToonStats.orElse(null);
    }

    @SchemaMapping(typeName = "TTRAPIResponse", field = "districts")
    public Districts getDistricts(final TTRAPIResponse response) {
        final Optional<Districts> optionalDistricts = this.cache.get(CacheKey.Districts);
        return optionalDistricts.orElse(null);
    }

}
