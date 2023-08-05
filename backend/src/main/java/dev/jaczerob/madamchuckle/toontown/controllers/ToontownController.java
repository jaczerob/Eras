package dev.jaczerob.madamchuckle.toontown.controllers;

import dev.jaczerob.madamchuckle.toontown.models.fieldoffices.FieldOffices;
import dev.jaczerob.madamchuckle.toontown.models.news.News;
import dev.jaczerob.madamchuckle.toontown.models.population.Population;
import dev.jaczerob.madamchuckle.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.madamchuckle.toontown.models.status.Status;
import dev.jaczerob.madamchuckle.toontown.services.cache.CacheKey;
import dev.jaczerob.madamchuckle.toontown.services.cache.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/toontown")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class ToontownController {
    private final CacheService cache;

    public ToontownController(final CacheService cache) {
        this.cache = cache;
    }

    @GetMapping("/population")
    public ResponseEntity<Population> getPopulation() {
        return ResponseEntity.ok((Population) cache.get(CacheKey.Population));
    }

    @GetMapping("/news")
    public ResponseEntity<News> getNews() {
        return ResponseEntity.ok((News) cache.get(CacheKey.News));
    }

    @GetMapping("/releasenotes")
    public ResponseEntity<ReleaseNotes> getReleaseNotes() {
        return ResponseEntity.ok((ReleaseNotes) cache.get(CacheKey.ReleaseNotes));
    }

    @GetMapping("/fieldoffices")
    public ResponseEntity<FieldOffices> getFieldOffices() {
        return ResponseEntity.ok((FieldOffices) cache.get(CacheKey.FieldOffices));
    }

    @GetMapping("/status")
    public ResponseEntity<Status> getStatus() {
        return ResponseEntity.ok((Status) cache.get(CacheKey.Status));
    }
}
