package dev.jaczerob.madamchuckle.server.toontown.models.status;

import dev.jaczerob.madamchuckle.server.toontown.models.ToontownObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Status extends ToontownObject {
    private boolean open;
    private String status;
}
