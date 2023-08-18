package dev.jaczerob.eras.server.toontown.controllers;

import dev.jaczerob.eras.server.cache.CacheKey;
import dev.jaczerob.eras.server.cache.CacheService;
import dev.jaczerob.eras.server.toontown.models.fieldoffices.FieldOffices;
import dev.jaczerob.eras.server.toontown.models.news.News;
import dev.jaczerob.eras.server.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.eras.server.toontown.models.status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ToontownController {
    private final CacheService cache;

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
