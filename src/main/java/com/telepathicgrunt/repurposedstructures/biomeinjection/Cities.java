package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Cities {
    private Cities() {}

    public static void addCities() {

        BiomeInjection.addStructure(RSConfiguredStructures.CITY_NETHER, (event) ->
                (RepurposedStructures.RSAllConfig.RSCitiesConfig.cityNetherAverageChunkDistance != 1001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.CITY_NETHER,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.CITY_OVERWORLD, (event) ->
                RepurposedStructures.RSAllConfig.RSCitiesConfig.cityOverworldAverageChunkDistance != 10001 &&
                BiomeSelection.isBiomeAllowed(event, RSStructures.CITY_OVERWORLD,
                () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.JUNGLE) &&
                event.getBiome().getPrecipitation() != Biome.Precipitation.SNOW) &&
                !BiomeSelection.hasName(event, "dark", "spooky", "dead", "haunted", "evil", "witch", "ominous", "ebony") &&
                !BiomeSelection.isBiome(event, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST, Biomes.FLOWER_FOREST, Biomes.WINDSWEPT_FOREST, Biomes.TAIGA, Biomes.GROVE)
        );
    }
}
