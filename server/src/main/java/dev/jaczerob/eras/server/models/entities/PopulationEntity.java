package dev.jaczerob.eras.server.models.entities;

import dev.jaczerob.eras.server.models.ttr.districts.Districts;
import dev.jaczerob.eras.server.utils.ToontownTime;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "population")
public class PopulationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int population;
    private LocalDateTime date;

    public PopulationEntity() {
    }

    private PopulationEntity(int population) {
        this.population = population;
        this.date = ToontownTime.now();
    }

    public static PopulationEntity from(final Districts districts) {
        return new PopulationEntity(districts.getTotalPopulation());
    }

    public int getId() {
        return this.id;
    }

    public int getPopulation() {
        return this.population;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
