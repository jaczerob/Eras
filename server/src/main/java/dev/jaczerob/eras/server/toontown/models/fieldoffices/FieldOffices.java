package dev.jaczerob.eras.server.toontown.models.fieldoffices;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jaczerob.eras.server.utils.ToontownObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class FieldOffices extends ToontownObject {
    private static final Map<String, String> zoneIDLookup;

    static {
        zoneIDLookup = new HashMap<>();
        zoneIDLookup.put("3100", "Walrus Way");
        zoneIDLookup.put("3200", "Sleet Street");
        zoneIDLookup.put("3300", "Polar Place");
        zoneIDLookup.put("4100", "Alto Avenue");
        zoneIDLookup.put("4200", "Baritone Boulevard");
        zoneIDLookup.put("4300", "Tenor Terrace");
        zoneIDLookup.put("5100", "Elm Street");
        zoneIDLookup.put("5200", "Maple Street");
        zoneIDLookup.put("5300", "Oak Street");
        zoneIDLookup.put("9100", "Lullaby Lane");
        zoneIDLookup.put("9200", "Pajama Place");
    }

    private int lastUpdated;
    private Map<String, FieldOffice> fieldOffices;

    private static String convertZoneIDToZone(Map.Entry<String, FieldOffice> entry) {
        return zoneIDLookup.getOrDefault(entry.getKey(), "Zone Not Found");
    }

    public FieldOffices(
            @JsonProperty("lastUpdated") int lastUpdated,
            @JsonProperty("fieldOffices") Map<String, FieldOffice> fieldOffices
    ) {
        this.lastUpdated = lastUpdated;

        final var comparator = Comparator.comparing(FieldOffice::getDifficulty)
                .thenComparing(FieldOffice::getAnnexes);

        this.fieldOffices = fieldOffices.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(comparator))
                .collect(Collectors.toMap(FieldOffices::convertZoneIDToZone, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
