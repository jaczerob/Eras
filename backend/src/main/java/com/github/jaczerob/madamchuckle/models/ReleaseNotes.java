package com.github.jaczerob.madamchuckle.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ReleaseNotes extends ReleaseNotesPartial {
    private String body;
}
