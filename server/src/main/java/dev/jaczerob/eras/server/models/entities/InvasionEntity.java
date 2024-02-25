package dev.jaczerob.eras.server.models.entities;

import dev.jaczerob.eras.server.models.ttr.districts.District;
import dev.jaczerob.eras.server.models.ttr.invasions.Invasion;
import dev.jaczerob.eras.server.utils.ToontownTime;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public InvasionEntity() {
    }

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
                invasion.cog(),
                invasion.totalCogs()
        );

        return Optional.of(entity);
    }

    public int getId() {
        return this.id;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getCog() {
        return this.cog;
    }

    public int getTotalCogs() {
        return this.totalCogs;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public void setCog(final String cog) {
        this.cog = cog;
    }

    public void setTotalCogs(final int totalCogs) {
        this.totalCogs = totalCogs;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }
}
