package dev.jaczerob.eras.server.toonstats.models;

import dev.jaczerob.eras.server.toonstats.repositories.entities.ToonEntity;
import lombok.Data;

@Data
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
}