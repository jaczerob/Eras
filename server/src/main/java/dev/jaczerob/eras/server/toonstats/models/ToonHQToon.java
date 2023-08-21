package dev.jaczerob.eras.server.toonstats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
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
}
