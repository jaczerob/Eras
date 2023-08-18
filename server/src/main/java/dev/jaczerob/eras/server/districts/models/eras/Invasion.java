package dev.jaczerob.eras.server.districts.models.eras;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Invasion {
    private final int cogsDefeated;
    private final int totalCogs;
    private final String cog;
}
