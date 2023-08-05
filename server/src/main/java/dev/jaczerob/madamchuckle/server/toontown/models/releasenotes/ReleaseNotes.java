package dev.jaczerob.madamchuckle.server.toontown.models.releasenotes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReleaseNotes extends ReleaseNotesPartial {
    private String body;
}
