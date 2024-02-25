package dev.jaczerob.eras.server.models.ttr.fieldoffices;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

public class FieldOffice {
    private static final Map<String, String> DEPARTMENT_MAPPING = Map.of(
            "c", "Cashbot",
            "l", "Lawbot",
            "b", "Bossbot",
            "s", "Sellbot"
    );

    private String zone;
    private String department;
    private int difficulty;
    private int annexes;
    private boolean open;
    private int expiring;

    public FieldOffice() {
        this.department = "Unknown";
        this.difficulty = 0;
        this.annexes = 0;
        this.open = false;
        this.expiring = 0;
    }

    public FieldOffice(final String zone, final String department, final int difficulty, final int annexes, final boolean open, final int expiring) {
        this.zone = zone;
        this.department = department;
        this.difficulty = difficulty;
        this.annexes = annexes;
        this.open = open;
        this.expiring = expiring;
    }

    public String getZone() {
        return this.zone;
    }

    @JsonSetter("difficulty")
    public void setDifficulty(final int difficulty) {
        this.difficulty = difficulty + 1;
    }

    @JsonSetter("department")
    public void setDepartment(final String department) {
        this.department = DEPARTMENT_MAPPING.getOrDefault(department, "Unknown");
    }

    public String getDepartment() {
        return this.department;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public int getAnnexes() {
        return this.annexes;
    }

    public boolean getOpen() {
        return this.open;
    }

    public int getExpiring() {
        return this.expiring;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public void setExpiring(final int expiring) {
        this.expiring = expiring;
    }

    public void setAnnexes(final int annexes) {
        this.annexes = annexes;
    }

    public void setZone(final String zone) {
        this.zone = zone;
    }
}
