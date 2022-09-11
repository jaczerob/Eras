package com.github.jaczerob.madamchuckle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jaczerob.madamchuckle.clients.ToontownClient;
import com.github.jaczerob.madamchuckle.models.FieldOffices;
import com.github.jaczerob.madamchuckle.models.LoginInfo;
import com.github.jaczerob.madamchuckle.models.LoginResponse;
import com.github.jaczerob.madamchuckle.models.News;
import com.github.jaczerob.madamchuckle.models.Population;
import com.github.jaczerob.madamchuckle.models.QueueToken;
import com.github.jaczerob.madamchuckle.models.ReleaseNotes;
import com.github.jaczerob.madamchuckle.services.CacheService;

@RestController
@RequestMapping("/api/toontown")
@CrossOrigin(origins = { "http://localhost:4200" }, allowCredentials = "true")
public class ToontownController {
    @Autowired private ToontownClient toontown;
    @Autowired private CacheService cache;
    
    @GetMapping("/population")
    public ResponseEntity<Population> getPopulation() {
        return ResponseEntity.ok(cache.getPopulation());
    }

    @GetMapping("/news")
    public ResponseEntity<News> getNews() {
        return ResponseEntity.ok(cache.getNews());
    }

    @GetMapping("/releasenotes")
    public ResponseEntity<ReleaseNotes> getReleaseNotes() {
        return ResponseEntity.ok(cache.getReleaseNotes());
    }

    @GetMapping("/fieldoffices")
    public ResponseEntity<FieldOffices> getFieldOffices() {
        return ResponseEntity.ok(cache.getFieldOffices());
    }

    @PostMapping(value="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginInfo loginInfo) {
        LoginResponse loginResponse = toontown.login(loginInfo);
        if (loginResponse.getSuccess().equals("false") || loginResponse.getSuccess().equals("partial"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);

        return ResponseEntity.accepted().body(loginResponse);
    }

    @PostMapping(value="/updateQueue", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> updateQueue(@RequestBody QueueToken queueToken) throws InterruptedException {
        LoginResponse loginResponse = toontown.updateQueue(queueToken);
        if (loginResponse.getSuccess().equals("false"))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);

        return ResponseEntity.accepted().body(loginResponse);
    }
}
