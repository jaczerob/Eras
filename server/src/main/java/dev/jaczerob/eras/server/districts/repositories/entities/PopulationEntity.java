package dev.jaczerob.eras.server.districts.repositories.entities;

import dev.jaczerob.eras.server.districts.models.eras.Districts;
import dev.jaczerob.eras.server.utils.ToontownTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "population")
public class PopulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int population;
    private LocalDateTime date;

    private PopulationEntity(int population) {
        this.population = population;
        this.date = ToontownTime.now();
    }

    public static PopulationEntity from(final Districts districts) {
        return new PopulationEntity(districts.getTotalPopulation());
    }
}
