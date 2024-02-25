package dev.jaczerob.eras.server.models.ttr.releasenotes;

public class ReleaseNotes extends ReleaseNotesPartial {
    private String body;

    public ReleaseNotes() {
        super();
        this.body = "unknown";
    }

    public ReleaseNotes(final int noteId, final String slug, final String date, final String body) {
        super(noteId, slug, date);
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(final String body) {
        this.body = body;
    }
}
