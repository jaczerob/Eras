package com.github.jaczerob.madamchuckle.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name="total_population")
public class TotalPopulationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int population;
    private LocalDateTime date;

    public TotalPopulationEntity(int population) {
        this.population = population;
        this.date = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
    }
}
