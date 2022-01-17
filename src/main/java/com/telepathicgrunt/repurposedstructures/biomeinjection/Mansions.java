package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSMansionsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Mansions {
    private Mansions() {}

    public static void addMansions(TemporaryBiomeInjection.BiomeInjectionHelper event) {
        if (RSMansionsConfig.mansionBirchAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_BIRCH.get(),
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.MANSION_BIRCH);
        }

        if (RSMansionsConfig.mansionJungleAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_JUNGLE.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_JUNGLE);
        }

        if (RSMansionsConfig.mansionOakAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_OAK.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                        !BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.MANSION_OAK);
        }

        if (RSMansionsConfig.mansionSavannaAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_SAVANNA.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SAVANNA) &&
                        !BiomeSelection.isBiomeTemp(event, Biomes.SAVANNA_PLATEAU)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_SAVANNA);
        }

        if (RSMansionsConfig.mansionTaigaAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_TAIGA.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && event.biome.getPrecipitation() != Biome.Precipitation.SNOW &&
                        !BiomeSelection.hasNameTemp(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "glacial", "frozen")))
        {
            event.addStructure(RSConfiguredStructures.MANSION_TAIGA);
        }

        if (RSMansionsConfig.mansionDesertAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_DESERT.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_DESERT);
        }

        if (RSMansionsConfig.mansionSnowyAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_SNOWY.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                        (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && event.biome.getPrecipitation() == Biome.Precipitation.SNOW)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_SNOWY);
        }
    }
}
