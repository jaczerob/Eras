package dev.jaczerob.eras.server.models.toonstats;

import dev.jaczerob.eras.server.models.entities.ToonEntity;

public class Toon {
    private int species;
    private int laff;
    private int gagToonup;
    private int gagTrap;
    private int gagLure;
    private int gagSound;
    private int gagThrow;
    private int gagSquirt;
    private int gagDrop;
    private int organic;
    private int sellbot;
    private int cashbot;
    private int lawbot;
    private int bossbot;

    public static Toon fromEntity(final ToonEntity toonEntity) {
        final var toon = new Toon();

        toon.setSpecies(toonEntity.getSpecies());
        toon.setLaff(toonEntity.getLaff());
        toon.setGagToonup(toonEntity.getGagToonup());
        toon.setGagTrap(toonEntity.getGagTrap());
        toon.setGagLure(toonEntity.getGagLure());
        toon.setGagSound(toonEntity.getGagSound());
        toon.setGagThrow(toonEntity.getGagThrow());
        toon.setGagSquirt(toonEntity.getGagSquirt());
        toon.setGagDrop(toonEntity.getGagDrop());
        toon.setOrganic(toonEntity.getOrganic());
        toon.setSellbot(toonEntity.getSellbot());
        toon.setCashbot(toonEntity.getCashbot());
        toon.setLawbot(toonEntity.getLawbot());
        toon.setBossbot(toonEntity.getBossbot());

        return toon;
    }

    public int getSpecies() {
        return this.species;
    }

    public int getLaff() {
        return this.laff;
    }

    public int getGagToonup() {
        return this.gagToonup;
    }

    public int getGagTrap() {
        return this.gagTrap;
    }

    public int getGagLure() {
        return this.gagLure;
    }

    public int getGagSound() {
        return this.gagSound;
    }

    public int getGagThrow() {
        return this.gagThrow;
    }

    public int getGagSquirt() {
        return this.gagSquirt;
    }

    public int getGagDrop() {
        return this.gagDrop;
    }

    public int getOrganic() {
        return this.organic;
    }

    public int getSellbot() {
        return this.sellbot;
    }

    public int getCashbot() {
        return this.cashbot;
    }

    public int getLawbot() {
        return this.lawbot;
    }

    public int getBossbot() {
        return this.bossbot;
    }

    public void setSpecies(final int species) {
        this.species = species;
    }

    public void setLaff(final int laff) {
        this.laff = laff;
    }

    public void setGagToonup(final int gagToonup) {
        this.gagToonup = gagToonup;
    }

    public void setGagTrap(final int gagTrap) {
        this.gagTrap = gagTrap;
    }

    public void setGagLure(final int gagLure) {
        this.gagLure = gagLure;
    }

    public void setGagSound(final int gagSound) {
        this.gagSound = gagSound;
    }

    public void setGagThrow(final int gagThrow) {
        this.gagThrow = gagThrow;
    }

    public void setGagSquirt(final int gagSquirt) {
        this.gagSquirt = gagSquirt;
    }

    public void setGagDrop(final int gagDrop) {
        this.gagDrop = gagDrop;
    }

    public void setOrganic(final int organic) {
        this.organic = organic;
    }

    public void setSellbot(final int sellbot) {
        this.sellbot = sellbot;
    }

    public void setCashbot(final int cashbot) {
        this.cashbot = cashbot;
    }

    public void setLawbot(final int lawbot) {
        this.lawbot = lawbot;
    }

    public void setBossbot(final int bossbot) {
        this.bossbot = bossbot;
    }
}