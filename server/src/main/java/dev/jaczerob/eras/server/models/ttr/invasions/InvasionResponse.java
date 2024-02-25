package dev.jaczerob.eras.server.models.ttr.invasions;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jaczerob.eras.server.models.ttr.ToontownAPIObject;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InvasionResponse extends ToontownAPIObject {
    private final Map<String, InvadedDistrictResponse> invadedDistricts;

    public InvasionResponse(
            @JsonProperty("error") final String error,
            @JsonProperty("lastUpdated") final int lastUpdated,
            @JsonProperty("invasions") final Map<String, Map<String, String>> invasions
    ) {
        super(error, lastUpdated);

        this.invadedDistricts = invasions.entrySet().stream()
            .map(invasionEntry -> {
                final var district = invasionEntry.getKey();
                final var invasion = invasionEntry.getValue();

                final var progress = invasion.get("progress");
                final var progressParts = progress.split("/");
                final var cogsDefeated = Integer.parseInt(progressParts[0]);
                final var totalCogs = Integer.parseInt(progressParts[1]);

                return new InvadedDistrictResponse(
                        district,
                        Integer.parseInt(invasion.get("asOf")),
                        invasion.get("type"),
                        cogsDefeated,
                        totalCogs
                );
            })
            .collect(Collectors.toMap(InvadedDistrictResponse::getDistrict, Function.identity()));
    }

    public Map<String, InvadedDistrictResponse> getInvadedDistricts() {
        return this.invadedDistricts;
    }
}
