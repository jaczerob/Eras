package dev.jaczerob.eras.server.districts.models.eras;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@RequiredArgsConstructor
public class District {
    private final String name;
    private final int population;
    private final Status status;

    @Nullable
    private final Invasion invasion;
}
