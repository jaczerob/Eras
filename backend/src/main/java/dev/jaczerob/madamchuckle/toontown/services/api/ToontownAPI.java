package dev.jaczerob.madamchuckle.toontown.services.api;

import dev.jaczerob.madamchuckle.toontown.models.fieldoffices.FieldOffices;
import dev.jaczerob.madamchuckle.toontown.models.news.News;
import dev.jaczerob.madamchuckle.toontown.models.population.Population;
import dev.jaczerob.madamchuckle.toontown.models.releasenotes.ReleaseNotes;
import dev.jaczerob.madamchuckle.toontown.models.releasenotes.ReleaseNotesPartial;
import dev.jaczerob.madamchuckle.toontown.models.status.Status;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "toontown", url = "https://www.toontownrewritten.com/api")
public interface ToontownAPI {
    @RequestMapping("/fieldoffices")
    FieldOffices getFieldOffices();

    @RequestMapping("/releasenotes")
    List<ReleaseNotesPartial> getReleaseNotes();

    @RequestMapping("/releasenotes/{id}")
    ReleaseNotes getReleaseNotes(@Param("id") int id);

    @RequestMapping("/status")
    Status getStatus();

    @RequestMapping("/population")
    Population getPopulation();

    @RequestMapping("/news")
    News getNews();
}
