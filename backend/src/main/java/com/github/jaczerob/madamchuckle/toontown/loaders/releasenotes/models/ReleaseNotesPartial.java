package com.github.jaczerob.madamchuckle.toontown.loaders.releasenotes.models;

import com.github.jaczerob.madamchuckle.toontown.models.ToontownObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ReleaseNotesPartial extends ToontownObject {
    private int noteId;
    private String slug;
    private String date;
}
