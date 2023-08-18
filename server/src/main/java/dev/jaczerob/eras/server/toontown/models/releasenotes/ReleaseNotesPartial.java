package dev.jaczerob.eras.server.toontown.models.releasenotes;

import dev.jaczerob.eras.server.utils.ToontownObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseNotesPartial extends ToontownObject {
    private int noteId;
    private String slug;
    private String date;
}
