package dev.jaczerob.eras.server.controllers;

import dev.jaczerob.eras.server.models.toonstats.ToonStats;
import dev.jaczerob.eras.server.models.ttr.districts.Districts;
import dev.jaczerob.eras.server.models.ttr.fieldoffices.FieldOffices;
import dev.jaczerob.eras.server.models.ttr.news.News;
import dev.jaczerob.eras.server.models.ttr.releasenotes.ReleaseNotes;
import dev.jaczerob.eras.server.models.ttr.status.Status;

public class TTRAPIResponse {
    public News news;
    public ReleaseNotes releaseNotes;
    public Status status;
    public ToonStats toonStats;
    public Districts districts;
    public FieldOffices fieldOffices;

    public TTRAPIResponse() {}

    public News getNews() {
        return this.news;
    }

    public FieldOffices getFieldOffices() {
        return this.fieldOffices;
    }

    public void setFieldOffices(final FieldOffices fieldOffices) {
        this.fieldOffices = fieldOffices;
    }

    public void setNews(final News news) {
        this.news = news;
    }

    public ReleaseNotes getReleaseNotes() {
        return this.releaseNotes;
    }

    public void setReleaseNotes(final ReleaseNotes releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public ToonStats getToonStats() {
        return this.toonStats;
    }

    public void setToonStats(final ToonStats toonStats) {
        this.toonStats = toonStats;
    }

    public Districts getDistricts() {
        return this.districts;
    }

    public void setDistricts(final Districts districts) {
        this.districts = districts;
    }
}
