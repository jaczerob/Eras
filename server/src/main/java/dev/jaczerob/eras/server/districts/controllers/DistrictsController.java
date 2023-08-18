package dev.jaczerob.eras.server.districts.controllers;

import dev.jaczerob.eras.server.cache.CacheKey;
import dev.jaczerob.eras.server.cache.CacheService;
import dev.jaczerob.eras.server.districts.models.eras.Districts;
import dev.jaczerob.eras.server.districts.services.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/districts")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DistrictsController {
    private final CacheService cache;
    private final DistrictService districtService;

    @GetMapping
    public ResponseEntity<Districts> getDistricts() {
        return ResponseEntity.ok((Districts) cache.get(CacheKey.Districts));
    }
}
