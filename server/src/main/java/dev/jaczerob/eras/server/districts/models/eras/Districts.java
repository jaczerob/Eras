package dev.jaczerob.eras.server.districts.models.eras;

import dev.jaczerob.eras.server.utils.ToontownObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Districts extends ToontownObject {
    private final int totalPopulation;
    private final List<District> districts;
}
