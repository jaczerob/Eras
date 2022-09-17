package com.github.jaczerob.madamchuckle.toontown.loaders.releasenotes.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReleaseNotes extends ReleaseNotesPartial {
    private String body;
}
