package com.github.jaczerob.madamchuckle.models;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Data;

@Data
public class FieldOffice {

    private String department;
    private int difficulty;
    private int annexes;
    private boolean open;
    private int expiring;

    @JsonSetter("difficulty")
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty + 1;
    }
}
