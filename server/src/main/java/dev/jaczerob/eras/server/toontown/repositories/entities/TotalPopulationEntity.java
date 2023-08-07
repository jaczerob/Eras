package dev.jaczerob.eras.server.toontown.repositories.entities;

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
@Table(name = "total_population")
public class TotalPopulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int population;
    private LocalDateTime date;

    public TotalPopulationEntity(int population) {
        this.population = population;
        this.date = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
    }
}
