package dev.jaczerob.eras.server.toonstats.services;

import dev.jaczerob.eras.server.toonstats.models.Toon;
import dev.jaczerob.eras.server.toonstats.models.ToonStats;
import dev.jaczerob.eras.server.toonstats.repositories.ToonRepository;
import dev.jaczerob.eras.server.toonstats.repositories.entities.ToonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ToonStatsService {
    private static final int MAX_LAFF = 140;

    private final ToonRepository toonRepository;

    public ToonStats getToonStats() {
        final List<ToonEntity> toonEntities = toonRepository.findAll();
        final List<Toon> toons = toonEntities.stream()
                .map(Toon::fromEntity)
                .toList();

        final ToonStats toonStats = new ToonStats();

        for (final Toon toon : toons) {
            toonStats.amountToons += 1;

            switch (toon.getSpecies()) {
                case 1 -> toonStats.amountBears += 1;
                case 2 -> toonStats.amountCats += 1;
                case 3 -> toonStats.amountDogs += 1;
                case 4 -> toonStats.amountDucks += 1;
                case 5 -> toonStats.amountHorses += 1;
                case 6 -> toonStats.amountMonkeys += 1;
                case 7 -> toonStats.amountMice += 1;
                case 8 -> toonStats.amountPigs += 1;
                case 9 -> toonStats.amountRabbits += 1;
                case 10 -> toonStats.amountDeer += 1;
                case 11 -> toonStats.amountCrocodiles += 1;
            }

            toonStats.amountToonsWithToonUp += Math.min(toon.getGagToonup(), 1);
            toonStats.amountToonsWithTrap += Math.min(toon.getGagTrap(), 1);
            toonStats.amountToonsWithLure += Math.min(toon.getGagLure(), 1);
            toonStats.amountToonsWithSound += Math.min(toon.getGagSound(), 1);
            toonStats.amountToonsWithThrow += Math.min(toon.getGagThrow(), 1);
            toonStats.amountToonsWithSquirt += Math.min(toon.getGagSquirt(), 1);
            toonStats.amountToonsWithDrop += Math.min(toon.getGagDrop(), 1);

            if (toon.getSellbot() == 8) {
                toonStats.amountTier8Sellbot += 1;
            }

            if (toon.getCashbot() == 8) {
                toonStats.amountTier8Cashbot += 1;
            }

            if (toon.getLawbot() == 8) {
                toonStats.amountTier8Lawbot += 1;
            }

            if (toon.getBossbot() == 8) {
                toonStats.amountTier8Bossbot += 1;
            }

            switch (toon.getOrganic()) {
                case 0 -> toonStats.amountOrganicNone += 1;
                case 1 -> toonStats.amountOrganicToonUp += 1;
                case 2 -> toonStats.amountOrganicTrap += 1;
                case 3 -> toonStats.amountOrganicLure += 1;
                case 4 -> toonStats.amountOrganicSound += 1;
                case 5 -> toonStats.amountOrganicThrow += 1;
                case 6 -> toonStats.amountOrganicSquirt += 1;
                case 7 -> toonStats.amountOrganicDrop += 1;
            }
            
            if (toon.getLaff() >= MAX_LAFF) {
                toonStats.amountToonsMaxed += 1;

                switch (toon.getSpecies()) {
                    case 1 -> toonStats.amountBearsMaxed += 1;
                    case 2 -> toonStats.amountCatsMaxed += 1;
                    case 3 -> toonStats.amountDogsMaxed += 1;
                    case 4 -> toonStats.amountDucksMaxed += 1;
                    case 5 -> toonStats.amountHorsesMaxed += 1;
                    case 6 -> toonStats.amountMonkeysMaxed += 1;
                    case 7 -> toonStats.amountMiceMaxed += 1;
                    case 8 -> toonStats.amountPigsMaxed += 1;
                    case 9 -> toonStats.amountRabbitsMaxed += 1;
                    case 10 -> toonStats.amountDeerMaxed += 1;
                    case 11 -> toonStats.amountCrocodilesMaxed += 1;
                }

                toonStats.amountToonsWithToonUpMaxed += Math.min(toon.getGagToonup(), 1);
                toonStats.amountToonsWithTrapMaxed += Math.min(toon.getGagTrap(), 1);
                toonStats.amountToonsWithLureMaxed += Math.min(toon.getGagLure(), 1);
                toonStats.amountToonsWithSoundMaxed += Math.min(toon.getGagSound(), 1);
                toonStats.amountToonsWithThrowMaxed += Math.min(toon.getGagThrow(), 1);
                toonStats.amountToonsWithSquirtMaxed += Math.min(toon.getGagSquirt(), 1);
                toonStats.amountToonsWithDropMaxed += Math.min(toon.getGagDrop(), 1);
                
            } else if (toon.getLaff() >= 100) {
                toonStats.amountToonsAbove100Laff += 1;

                switch (toon.getSpecies()) {
                    case 1 -> toonStats.amountBearsAbove100Laff += 1;
                    case 2 -> toonStats.amountCatsAbove100Laff += 1;
                    case 3 -> toonStats.amountDogsAbove100Laff += 1;
                    case 4 -> toonStats.amountDucksAbove100Laff += 1;
                    case 5 -> toonStats.amountHorsesAbove100Laff += 1;
                    case 6 -> toonStats.amountMonkeysAbove100Laff += 1;
                    case 7 -> toonStats.amountMiceAbove100Laff += 1;
                    case 8 -> toonStats.amountPigsAbove100Laff += 1;
                    case 9 -> toonStats.amountRabbitsAbove100Laff += 1;
                    case 10 -> toonStats.amountDeerAbove100Laff += 1;
                    case 11 -> toonStats.amountCrocodilesAbove100Laff += 1;
                }

                toonStats.amountToonsWithToonUpAbove100Laff += Math.min(toon.getGagToonup(), 1);
                toonStats.amountToonsWithTrapAbove100Laff += Math.min(toon.getGagTrap(), 1);
                toonStats.amountToonsWithLureAbove100Laff += Math.min(toon.getGagLure(), 1);
                toonStats.amountToonsWithSoundAbove100Laff += Math.min(toon.getGagSound(), 1);
                toonStats.amountToonsWithThrowAbove100Laff += Math.min(toon.getGagThrow(), 1);
                toonStats.amountToonsWithSquirtAbove100Laff += Math.min(toon.getGagSquirt(), 1);
                toonStats.amountToonsWithDropAbove100Laff += Math.min(toon.getGagDrop(), 1);
            }
        }

        return toonStats;
    }
}
