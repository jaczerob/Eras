package dev.jaczerob.eras.server.models.ttr.fieldoffices;

import com.fasterxml.jackson.annotation.JsonSetter;
import dev.jaczerob.eras.server.models.ttr.ToontownObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FieldOffices extends ToontownObject {
    private static final Map<String, String> ZONE_ID_LOOKUP;

    static {
        ZONE_ID_LOOKUP = new HashMap<>();
        ZONE_ID_LOOKUP.put("3100", "Walrus Way");
        ZONE_ID_LOOKUP.put("3200", "Sleet Street");
        ZONE_ID_LOOKUP.put("3300", "Polar Place");
        ZONE_ID_LOOKUP.put("4100", "Alto Avenue");
        ZONE_ID_LOOKUP.put("4200", "Baritone Boulevard");
        ZONE_ID_LOOKUP.put("4300", "Tenor Terrace");
        ZONE_ID_LOOKUP.put("5100", "Elm Street");
        ZONE_ID_LOOKUP.put("5200", "Maple Street");
        ZONE_ID_LOOKUP.put("5300", "Oak Street");
        ZONE_ID_LOOKUP.put("9100", "Lullaby Lane");
        ZONE_ID_LOOKUP.put("9200", "Pajama Place");
    }

    private int lastUpdated;
    private Set<FieldOffice> fieldOffices;

    private static String convertZoneIDToZone(final Map.Entry<String, FieldOffice> entry) {
        return ZONE_ID_LOOKUP.getOrDefault(entry.getKey(), "Unknown");
    }

    public FieldOffices(final int lastUpdated, final Map<String, FieldOffice> fieldOffices) {
        this.lastUpdated = lastUpdated;
    }

    public FieldOffices() {}

    public int getLastUpdated() {
        return this.lastUpdated;
    }

    public Set<FieldOffice> getFieldOffices() {
        return this.fieldOffices;
    }

    @JsonSetter("fieldOffices")
    public void setFieldOffices(final Map<String, FieldOffice> fieldOffices) {
        this.fieldOffices = fieldOffices.entrySet().stream()
                .map(entry -> {
                    entry.getValue().setZone(convertZoneIDToZone(entry));
                    return entry.getValue();
                })
                .collect(Collectors.toSet());

        this.fieldOffices.add(new FieldOffice("Kaboomberg", "Sellbot", 4, 150, true, 0));
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
