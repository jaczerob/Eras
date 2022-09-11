package com.github.jaczerob.madamchuckle.services;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jaczerob.madamchuckle.clients.ToontownClient;
import com.github.jaczerob.madamchuckle.models.FieldOffices;
import com.github.jaczerob.madamchuckle.models.News;
import com.github.jaczerob.madamchuckle.models.Population;
import com.github.jaczerob.madamchuckle.models.ReleaseNotes;
import com.github.jaczerob.madamchuckle.models.ReleaseNotesPartial;
import com.github.jaczerob.madamchuckle.models.ToontownObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class CacheService {
    private static final Logger logger = LogManager.getLogger(CacheService.class);

    @Autowired private ToontownClient toontownClient;

    private LoadingCache<String, ToontownObject> cache = CacheBuilder.newBuilder()
        .expireAfterWrite(60, TimeUnit.SECONDS)
        .build(
            new CacheLoader<String, ToontownObject>() {
                public ToontownObject load(String key) throws Exception {
                    switch (key) {
                        case "FIELD_OFFICES":
                        return toontownClient.getFieldOffices();
                        case "RELEASE_NOTES":
                        List<ReleaseNotesPartial> partialReleaseNotes = toontownClient.getReleaseNotes();
                        return toontownClient.getReleaseNote(partialReleaseNotes.get(0).getNoteId());
                        case "POPULATION":
                        return toontownClient.getPopulation();
                        case "NEWS":
                        return toontownClient.getNews();
                        default:
                        return null;
                    }
                }
            }
        );

    public FieldOffices getFieldOffices() {
        ToontownObject obj = this.getFromCache("FIELD_OFFICES");
        if (!(obj instanceof FieldOffices)) {
            logger.warn("object of type {} is not FieldOffices", obj);
            return null;
        }

        return (FieldOffices) obj;
    }

    public ReleaseNotes getReleaseNotes() {
        ToontownObject obj = this.getFromCache("RELEASE_NOTES");
        if (!(obj instanceof ReleaseNotes)) {
            logger.warn("object of type {} is not ReleaseNotes", obj);
            return null;
        }

        return (ReleaseNotes) obj;
    }

    public Population getPopulation() {
        ToontownObject obj = this.getFromCache("POPULATION");
        if (!(obj instanceof Population)) {
            logger.warn("object of type {} is not Population", obj);
            return null;
        }

        return (Population) obj;
    }

    public News getNews() {
        ToontownObject obj = this.getFromCache("NEWS");
        if (!(obj instanceof News)) {
            logger.warn("object of type {} is not News", obj);
            return null;
        }

        return (News) obj;
    }

    private ToontownObject getFromCache(String key) {
        try {
            return this.cache.get(key);
        } catch (ExecutionException error) {
            logger.error("error getting {}", key, error);
            return null;
        }
    }
}
