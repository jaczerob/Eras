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
@Table(name="district_population")
public class DistrictPopulationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int population;
    private String district;
    private LocalDateTime date;

    public DistrictPopulationEntity(String district, int population) {
        this.population = population;
        this.district = district;
        this.date = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
    }
}
