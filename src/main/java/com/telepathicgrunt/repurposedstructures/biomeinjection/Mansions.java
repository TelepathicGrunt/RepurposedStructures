package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Mansions {
    private Mansions() {}

    public static void addMansions(BiomeInjection.BiomeInjectionHelper event) {
        if (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionBirchAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_BIRCH,
                    () -> BiomeSelection.hasNameTemp(event, "birch")))
        {
            event.addStructure(RSConfiguredStructures.MANSION_BIRCH);
        }

        if (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionJungleAverageChunkDistance != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_JUNGLE,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.JUNGLE)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_JUNGLE);
        }

        if (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionOakAverageChunkDistance != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_OAK,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST) &&
                        !BiomeSelection.hasNameTemp(event, "birch", "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony")))
        {
            event.addStructure(RSConfiguredStructures.MANSION_OAK);
        }

        if (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSavannaAverageChunkDistance != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_SAVANNA,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.SAVANNA) &&
                        !BiomeSelection.isBiomeTemp(event, Biomes.SAVANNA_PLATEAU)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_SAVANNA);
        }

        if (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionTaigaAverageChunkDistance != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_TAIGA,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && event.biome.getPrecipitation() != Biome.Precipitation.SNOW &&
                        !BiomeSelection.hasNameTemp(event, "giant", "redwood", "snow", "ice", "icy", "glacier", "glacial", "frozen")))
        {
            event.addStructure(RSConfiguredStructures.MANSION_TAIGA);
        }

        if (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionDesertAverageChunkDistance != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_DESERT,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_DESERT);
        }

        if (RepurposedStructures.RSAllConfig.RSMansionsConfig.mansionSnowyAverageChunkDistance != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.MANSION_SNOWY,
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY) ||
                        (BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) && event.biome.getPrecipitation() == Biome.Precipitation.SNOW)))
        {
            event.addStructure(RSConfiguredStructures.MANSION_SNOWY);
        }
    }
}
