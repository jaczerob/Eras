package dev.jaczerob.madamchuckle.toontown.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "district_population")
public class DistrictPopulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
