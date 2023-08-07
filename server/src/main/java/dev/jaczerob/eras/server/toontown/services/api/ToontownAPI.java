package dev.jaczerob.eras.server.toontown.services.api;

import dev.jaczerob.eras.server.toontown.models.fieldoffices.FieldOffices;
import dev.jaczerob.eras.server.toontown.models.news.News;
import dev.jaczerob.eras.server.toontown.models.population.Population;
import dev.jaczerob.eras.server.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.eras.server.toontown.models.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.eras.server.toontown.models.status.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "toontown", url = "https://www.toontownrewritten.com/api")
public interface ToontownAPI {
    @RequestMapping("/fieldoffices")
    FieldOffices getFieldOffices();

    @RequestMapping("/releasenotes")
    List<ReleaseNotesPartial> getReleaseNotes();

    @RequestMapping("/releasenotes/{id}")
    ReleaseNotes getReleaseNotes(@PathVariable("id") long id);

    @RequestMapping("/status")
    Status getStatus();

    @RequestMapping("/population")
    Population getPopulation();

    @RequestMapping("/news")
    News getNews();
}
