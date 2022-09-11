package com.github.jaczerob.madamchuckle.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
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

    @JsonSetter("fieldOffices")
    public void setFieldOffices(Map<String, FieldOffice> fieldOffices) {
        this.fieldOffices = new HashMap<>();

        for (Entry<String, FieldOffice> fEntry : fieldOffices.entrySet()) {
            this.fieldOffices.put(zoneIDLookup.getOrDefault(fEntry.getKey(), "Zone Not Found"), fEntry.getValue());
        }
    }
}
