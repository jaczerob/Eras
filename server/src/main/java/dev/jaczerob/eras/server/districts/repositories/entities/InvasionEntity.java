package dev.jaczerob.eras.server.districts.repositories.entities;

import dev.jaczerob.eras.server.districts.models.eras.District;
import dev.jaczerob.eras.server.districts.models.eras.Invasion;
import dev.jaczerob.eras.server.utils.ToontownTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "invasions")
public class InvasionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String district;
    private String cog;

    @Column(name = "total_cogs")
    private int totalCogs;
    private LocalDateTime date;

    private InvasionEntity(
        final String district,
        final String cog,
        final int totalCogs
    ) {
        this.district = district;
        this.cog = cog;
        this.totalCogs = totalCogs;
        this.date = ToontownTime.now();
    }

    public static Optional<InvasionEntity> from(final District district) {
        if (district.getInvasion() == null) {
            return Optional.empty();
        }

        final Invasion invasion = district.getInvasion();
        final InvasionEntity entity =  new InvasionEntity(
                district.getName(),
                invasion.getCog(),
                invasion.getTotalCogs()
        );

        return Optional.of(entity);
    }
}
