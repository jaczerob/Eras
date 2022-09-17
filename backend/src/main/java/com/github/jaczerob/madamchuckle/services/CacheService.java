package com.github.jaczerob.madamchuckle.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.github.jaczerob.madamchuckle.annotations.ToontownAPILoader;
import com.github.jaczerob.madamchuckle.toontown.loaders.Loader;
import com.github.jaczerob.madamchuckle.toontown.models.ToontownObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class CacheService {
    private static final Logger logger = LogManager.getLogger(CacheService.class);

    @Autowired private ApplicationContext applicationContext;

    @Bean
    public Map<String, Loader<? extends ToontownObject>> getLoaders() {
        Map<String, Loader<? extends ToontownObject>> loaders = new HashMap<>();
        for (Entry<String, Object> entry : applicationContext.getBeansWithAnnotation(ToontownAPILoader.class).entrySet()) {
            if (!(entry.getValue() instanceof Loader<?>)) {
                logger.warn("Bean {} type mismatch: {} is not a Loader", entry.getKey(), entry.getValue().getClass().getName());
                continue;
            }

            logger.info("found loader: {}", entry.getKey());
            loaders.put(entry.getKey(), (Loader<?>) entry.getValue());
        }

        return loaders;
    }

    private LoadingCache<String, ToontownObject> cache = CacheBuilder.newBuilder()
        .expireAfterWrite(60, TimeUnit.SECONDS)
        .build(
            new CacheLoader<String, ToontownObject>() {
                public ToontownObject load(String key) throws Exception {
                    Loader<?> loader = getLoaders().get(key);
                    if (loader == null) {
                        logger.warn("no loader with key: {}", key);
                        return null;
                    }

                    return loader.load();
                }
            }
        );

    public ToontownObject get(String key) {
        try {
            return this.cache.get(key);
        } catch (ExecutionException exc) {
            logger.error("exception while loading key: {}", key, exc);
            return null;
        }
    }
}
