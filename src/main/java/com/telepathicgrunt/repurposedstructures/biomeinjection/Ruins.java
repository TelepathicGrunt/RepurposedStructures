package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Ruins {
    private Ruins() {}

    public static void addRuins() {

        BiomeInjection.addStructure(RSConfiguredStructures.RUINS_NETHER, (event) ->
            (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsNetherAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.RUINS_NETHER,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.RUINS_LAND_WARM, (event) ->
            (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandWarmAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.RUINS_LAND_WARM,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.SWAMP) &&
                    !BiomeSelection.hasName(event, "snow", "ice", "frozen") &&
                    event.getBiome().getBaseTemperature() >= 0.25f))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.RUINS_LAND_HOT, (event) ->
            (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandHotAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.RUINS_LAND_HOT,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.DESERT)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.RUINS_LAND_COLD, (event) ->
            (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandColdAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.RUINS_LAND_COLD,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.EXTREME_HILLS, Biome.BiomeCategory.MOUNTAIN) &&
                    event.getBiome().getPrecipitation() != Biome.Precipitation.SNOW))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.RUINS_LAND_ICY, (event) ->
            (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandIcyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.RUINS_LAND_ICY,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.ICY)))
        );
    }
}
