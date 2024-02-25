package dev.jaczerob.eras.server.models.toonstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ToonHQToon {
    private Integer id;
    private Integer game;
    private String photo;
    private Integer species;
    private Integer laff;
    private Integer toonup;
    private Integer trap;
    private Integer lure;
    private Integer sound;

    @JsonProperty("throw")
    private Integer throwGag;
    private Integer squirt;
    private Integer drop;
    private List<String> prestiges;
    private Integer sellbot;
    private Integer cashbot;
    private Integer lawbot;
    private Integer bossbot;

    public ToonHQToon() {
        this.id = 0;
        this.game = 0;
        this.photo = "";
        this.species = 0;
        this.laff = 0;
        this.toonup = 0;
        this.trap = 0;
        this.lure = 0;
        this.sound = 0;
        this.throwGag = 0;
        this.squirt = 0;
        this.drop = 0;
        this.prestiges = List.of();
        this.sellbot = 0;
        this.cashbot = 0;
        this.lawbot = 0;
        this.bossbot = 0;
    }

    public ToonHQToon(final Integer id, final Integer game, final String photo, final Integer species, final Integer laff, final Integer toonup, final Integer trap, final Integer lure, final Integer sound, final Integer throwGag, final Integer squirt, final Integer drop, final List<String> prestiges, final Integer sellbot, final Integer cashbot, final Integer lawbot, final Integer bossbot) {
        this.id = id;
        this.game = game;
        this.photo = photo;
        this.species = species;
        this.laff = laff;
        this.toonup = toonup;
        this.trap = trap;
        this.lure = lure;
        this.sound = sound;
        this.throwGag = throwGag;
        this.squirt = squirt;
        this.drop = drop;
        this.prestiges = prestiges;
        this.sellbot = sellbot;
        this.cashbot = cashbot;
        this.lawbot = lawbot;
        this.bossbot = bossbot;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getGame() {
        return this.game;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Integer getSpecies() {
        return this.species;
    }

    public Integer getLaff() {
        return this.laff;
    }

    public Integer getToonup() {
        return this.toonup;
    }

    public Integer getTrap() {
        return this.trap;
    }

    public Integer getLure() {
        return this.lure;
    }

    public Integer getSound() {
        return this.sound;
    }

    public Integer getThrowGag() {
        return this.throwGag;
    }

    public Integer getSquirt() {
        return this.squirt;
    }

    public Integer getDrop() {
        return this.drop;
    }

    public List<String> getPrestiges() {
        return this.prestiges;
    }

    public Integer getSellbot() {
        return this.sellbot;
    }

    public Integer getCashbot() {
        return this.cashbot;
    }

    public Integer getLawbot() {
        return this.lawbot;
    }

    public Integer getBossbot() {
        return this.bossbot;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setGame(final Integer game) {
        this.game = game;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public void setSpecies(final Integer species) {
        this.species = species;
    }

    public void setLaff(final Integer laff) {
        this.laff = laff;
    }

    public void setToonup(final Integer toonup) {
        this.toonup = toonup;
    }

    public void setTrap(final Integer trap) {
        this.trap = trap;
    }

    public void setLure(final Integer lure) {
        this.lure = lure;
    }

    public void setSound(final Integer sound) {
        this.sound = sound;
    }

    public void setThrowGag(final Integer throwGag) {
        this.throwGag = throwGag;
    }

    public void setSquirt(final Integer squirt) {
        this.squirt = squirt;
    }

    public void setDrop(final Integer drop) {
        this.drop = drop;
    }

    public void setPrestiges(final List<String> prestiges) {
        this.prestiges = prestiges;
    }

    public void setSellbot(final Integer sellbot) {
        this.sellbot = sellbot;
    }

    public void setCashbot(final Integer cashbot) {
        this.cashbot = cashbot;
    }

    public void setLawbot(final Integer lawbot) {
        this.lawbot = lawbot;
    }

    public void setBossbot(final Integer bossbot) {
        this.bossbot = bossbot;
    }
}
