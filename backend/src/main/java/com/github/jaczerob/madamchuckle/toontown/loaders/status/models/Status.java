package com.github.jaczerob.madamchuckle.toontown.loaders.status.models;

import com.github.jaczerob.madamchuckle.toontown.models.ToontownObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Status extends ToontownObject {
    private boolean open;
    private String status;
}
