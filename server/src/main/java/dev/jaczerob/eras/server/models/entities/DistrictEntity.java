package dev.jaczerob.eras.server.models.entities;

import dev.jaczerob.eras.server.models.ttr.districts.District;
import dev.jaczerob.eras.server.utils.ToontownTime;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "districts")
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int population;
    private String district;
    private String status;
    private LocalDateTime date;

    public DistrictEntity() {
    }

    public DistrictEntity(
            final int population,
            final String district,
            final String status
    ) {
        this.population = population;
        this.district = district;
        this.status = status;
        this.date = ToontownTime.now();
    }

    public static DistrictEntity from(final District district) {
        return new DistrictEntity(
                district.getPopulation(),
                district.getName(),
                district.getStatus().getValue()
        );
    }

    public int getId() {
        return this.id;
    }

    public int getPopulation() {
        return this.population;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getStatus() {
        return this.status;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setPopulation(final int population) {
        this.population = population;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }
}
