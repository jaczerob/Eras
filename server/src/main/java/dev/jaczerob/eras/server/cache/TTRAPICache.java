package dev.jaczerob.eras.server.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.jaczerob.eras.server.models.ttr.ToontownObject;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.eras.server.services.DistrictService;
import dev.jaczerob.eras.server.services.ToonStatsService;
import dev.jaczerob.eras.server.services.ToontownAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class TTRAPICache {
    private static final Logger logger = LogManager.getLogger(TTRAPICache.class);

    private final ToontownAPI toontownAPI;
    private final DistrictService districtService;
    private final ToonStatsService toonStatsService;

    public TTRAPICache(final ToontownAPI toontownAPI, final DistrictService districtService, final ToonStatsService toonStatsService) {
        this.toontownAPI = toontownAPI;
        this.districtService = districtService;
        this.toonStatsService = toonStatsService;
    }

    private final LoadingCache<CacheKey, ToontownObject> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .build(
                    new CacheLoader<>() {
                        @Override
                        public @NonNull ToontownObject load(final @NonNull CacheKey cacheKey) {
                            return switch (cacheKey) {
                                case Status -> toontownAPI.getStatus();
                                case Districts -> districtService.getDistricts();
                                case FieldOffices -> toontownAPI.getFieldOffices();
                                case ReleaseNotes -> {
                                    final List<ReleaseNotesPartial> releaseNotes = toontownAPI.getReleaseNotes();
                                    yield toontownAPI.getReleaseNotes(releaseNotes.getFirst().getNoteId());
                                }
                                case News -> toontownAPI.getNews();
                                case ToonStats -> toonStatsService.getToonStats();
                            };
                        }
                    }
            );

    public <T extends ToontownObject> Optional<T> get(final CacheKey key) {
        T obj;
        try {
            final ToontownObject toontownObject = this.cache.get(key);
            obj = (T) toontownObject;
        } catch (final ExecutionException exc) {
            logger.error("exception while loading key: {}", key, exc);
            obj = null;
        } catch (final ClassCastException exc) {
            logger.error("exception while casting key: {}", key, exc);
            obj = null;
        }

        return Optional.ofNullable(obj);
    }
}
