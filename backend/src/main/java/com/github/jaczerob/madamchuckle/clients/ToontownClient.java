package com.github.jaczerob.madamchuckle.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.jaczerob.madamchuckle.configurations.ToontownClientConfig;
import com.github.jaczerob.madamchuckle.models.FieldOffices;
import com.github.jaczerob.madamchuckle.models.LoginInfo;
import com.github.jaczerob.madamchuckle.models.LoginResponse;
import com.github.jaczerob.madamchuckle.models.News;
import com.github.jaczerob.madamchuckle.models.Population;
import com.github.jaczerob.madamchuckle.models.QueueToken;
import com.github.jaczerob.madamchuckle.models.ReleaseNotes;
import com.github.jaczerob.madamchuckle.models.ReleaseNotesPartial;

import feign.Headers;

@FeignClient(
    name="toontownClient", 
    url="https://www.toontownrewritten.com/api",
    configuration=ToontownClientConfig.class
)
public interface ToontownClient {
    @GetMapping("/population")
    public Population getPopulation();

    @GetMapping("/news")
    public News getNews();

    @GetMapping("/releasenotes")
    public List<ReleaseNotesPartial> getReleaseNotes();

    @GetMapping("/releasenotes/{id}")
    public ReleaseNotes getReleaseNote(@PathVariable("id") int id);

    @GetMapping("/fieldoffices")
    public FieldOffices getFieldOffices();

    @PostMapping(value="/login?format=json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public LoginResponse login(LoginInfo loginInfo);
    
    @PostMapping(value="/login?format=json")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    public LoginResponse updateQueue(QueueToken queueToken);

    @PostMapping(value="/login?format=json")
    @Headers({
        "Content-Type: application/x-www-form-urlencoded",
        "Accept: application/json; charset=utf-8"
    })
    public LoginResponse authenticate(@RequestParam("appToken") String appToken, @RequestParam("authToken") String authToken);
}
