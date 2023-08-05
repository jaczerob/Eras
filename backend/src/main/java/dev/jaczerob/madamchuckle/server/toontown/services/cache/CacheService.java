package dev.jaczerob.madamchuckle.server.toontown.services.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.jaczerob.madamchuckle.server.toontown.models.ToontownObject;
import dev.jaczerob.madamchuckle.server.toontown.models.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.madamchuckle.server.toontown.services.api.ToontownAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class CacheService {
    private static final Logger logger = LogManager.getLogger(CacheService.class);

    private final ToontownAPI toontownAPI;
    private final LoadingCache<CacheKey, ToontownObject> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .build(
                    new CacheLoader<>() {
                        @Override
                        public ToontownObject load(final CacheKey cacheKey) throws Exception {
                            return switch (cacheKey) {
                                case Status -> toontownAPI.getStatus();
                                case Population -> toontownAPI.getPopulation();
                                case FieldOffices -> toontownAPI.getFieldOffices();
                                case ReleaseNotes -> {
                                    final List<ReleaseNotesPartial> releaseNotes = toontownAPI.getReleaseNotes();
                                    yield toontownAPI.getReleaseNotes(releaseNotes.get(0).getNoteId());
                                }
                                case News -> toontownAPI.getNews();
                            };
                        }
                    }
            );

    public CacheService(final ToontownAPI toontownAPI) {
        this.toontownAPI = toontownAPI;
    }

    public ToontownObject get(final CacheKey key) {
        try {
            return this.cache.get(key);
        } catch (ExecutionException exc) {
            logger.error("exception while loading key: {}", key, exc);
            return null;
        }
    }
}
