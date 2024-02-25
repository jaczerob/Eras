package dev.jaczerob.eras.server.models.ttr.releasenotes;

import dev.jaczerob.eras.server.models.ttr.ToontownObject;

public class ReleaseNotesPartial extends ToontownObject {
    private int noteId;
    private String slug;
    private String date;

    public ReleaseNotesPartial() {
        this.noteId = 0;
        this.slug = "unknown";
        this.date = "unknown";
    }

    public ReleaseNotesPartial(final int noteId, final String slug, final String date) {
        this.noteId = noteId;
        this.slug = slug;
        this.date = date;
    }

    public int getNoteId() {
        return this.noteId;
    }

    public String getSlug() {
        return this.slug;
    }

    public String getDate() {
        return this.date;
    }

    public void setNoteId(final int noteId) {
        this.noteId = noteId;
    }

    public void setSlug(final String slug) {
        this.slug = slug;
    }

    public void setDate(final String date) {
        this.date = date;
    }
}
