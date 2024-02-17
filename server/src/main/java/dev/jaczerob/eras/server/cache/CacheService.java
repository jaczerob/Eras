package dev.jaczerob.eras.server.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.jaczerob.eras.server.districts.services.DistrictService;
import dev.jaczerob.eras.server.toonstats.services.ToonStatsService;
import dev.jaczerob.eras.server.toontown.models.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.eras.server.toontown.services.api.ToontownAPI;
import dev.jaczerob.eras.server.utils.ToontownObject;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class CacheService {
    private static final Logger logger = LogManager.getLogger(CacheService.class);

    private final ToontownAPI toontownAPI;
    private final DistrictService districtService;
    private final ToonStatsService toonStatsService;

    private final LoadingCache<CacheKey, ToontownObject> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .build(
                    new CacheLoader<>() {
                        @Override
                        public @NonNull ToontownObject load(@NonNull  final CacheKey cacheKey) {
                            return switch (cacheKey) {
                                case Status -> toontownAPI.getStatus();
                                case Districts -> districtService.getDistricts();
                                case FieldOffices -> toontownAPI.getFieldOffices();
                                case ReleaseNotes -> {
                                    final List<ReleaseNotesPartial> releaseNotes = toontownAPI.getReleaseNotes();
                                    yield toontownAPI.getReleaseNotes(releaseNotes.get(0).getNoteId());
                                }
                                case News -> toontownAPI.getNews();
                                case ToonStats -> toonStatsService.getToonStats();
                            };
                        }
                    }
            );

    public ToontownObject get(final CacheKey key) {
        try {
            return this.cache.get(key);
        } catch (ExecutionException exc) {
            logger.error("exception while loading key: {}", key, exc);
            return null;
        }
    }
}
