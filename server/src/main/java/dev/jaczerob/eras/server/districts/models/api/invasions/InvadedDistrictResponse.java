package dev.jaczerob.eras.server.districts.models.api.invasions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvadedDistrictResponse {
    private final String district;
    private final int asOf;
    private final String type;
    private final int cogsDefeated;
    private final int totalCogs;
}
