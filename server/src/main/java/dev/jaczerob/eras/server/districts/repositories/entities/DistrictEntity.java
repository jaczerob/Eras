package dev.jaczerob.eras.server.districts.repositories.entities;

import dev.jaczerob.eras.server.districts.models.eras.District;
import dev.jaczerob.eras.server.utils.ToontownTime;
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
@Table(name = "districts")
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int population;
    private String district;
    private String status;
    private LocalDateTime date;

    private DistrictEntity(
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
}
