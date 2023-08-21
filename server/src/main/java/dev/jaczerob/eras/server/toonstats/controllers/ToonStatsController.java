package dev.jaczerob.eras.server.toonstats.controllers;

import dev.jaczerob.eras.server.cache.CacheKey;
import dev.jaczerob.eras.server.cache.CacheService;
import dev.jaczerob.eras.server.toonstats.models.ToonStats;
import dev.jaczerob.eras.server.toonstats.repositories.ToonRepository;
import dev.jaczerob.eras.server.toonstats.repositories.entities.ToonEntity;
import dev.jaczerob.eras.server.toonstats.services.ToonStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/toonstats")
public class ToonStatsController {
    private final CacheService cacheService;

    @GetMapping
    public ResponseEntity<ToonStats> getToonStats() {
        return ResponseEntity.ok((ToonStats) cacheService.get(CacheKey.ToonStats));
    }
}
