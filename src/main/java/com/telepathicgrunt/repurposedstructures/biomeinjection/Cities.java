package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSCitiesConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Cities {
    private Cities() {}

    public static void addCities(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSCitiesConfig.citiesNetherAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.CITY_NETHER.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER)))
        {
            event.addStructure(RSConfiguredStructures.CITY_NETHER);
        }

        if (RSCitiesConfig.citiesOverworldAverageChunkDistance.get() != 10001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.CITY_OVERWORLD.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.JUNGLE) &&
                    event.biome.getPrecipitation() != Biome.Precipitation.SNOW) &&
                    !BiomeSelection.hasNameTemp(event, "dark", "spooky", "dead", "haunted") &&
                    !BiomeSelection.isBiomeTemp(event, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST, Biomes.WINDSWEPT_FOREST, Biomes.TAIGA))
        {
            event.addStructure(RSConfiguredStructures.CITY_OVERWORLD);
        }
    }
}
