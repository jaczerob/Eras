package com.github.jaczerob.madamchuckle.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReleaseNotesPartial extends ToontownObject {
    private int noteId;
    private String slug;
    private String date;
}
