package dev.jaczerob.eras.server.toontown.models.status;

import dev.jaczerob.eras.server.utils.ToontownObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Status extends ToontownObject {
    private boolean open;
    private String status;
}
