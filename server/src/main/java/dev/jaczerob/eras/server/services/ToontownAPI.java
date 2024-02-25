package dev.jaczerob.eras.server.services;

import dev.jaczerob.eras.server.models.ttr.fieldoffices.FieldOffices;
import dev.jaczerob.eras.server.models.ttr.invasions.InvasionResponse;
import dev.jaczerob.eras.server.models.ttr.news.News;
import dev.jaczerob.eras.server.models.ttr.population.PopulationResponse;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotes;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.eras.server.models.ttr.status.Status;
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

    @RequestMapping("/news")
    News getNews();

    @RequestMapping("/invasions")
    InvasionResponse getInvasions();

    @RequestMapping("/population")
    PopulationResponse getPopulation();
}
