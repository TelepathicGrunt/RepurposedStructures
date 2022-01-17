package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.configs.RSRuinsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Ruins {
    private Ruins() {}

    public static void addRuins(BiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsNetherAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_NETHER,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER)))
        {
            event.addStructure(RSConfiguredStructures.RUINS_NETHER);
        }

        if (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandWarmAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_LAND_WARM,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.SWAMP) &&
                    !BiomeSelection.hasNameTemp(event, "snow", "ice", "frozen") &&
                    event.biome.getBaseTemperature() >= 0.25f))
        {
            event.addStructure(RSConfiguredStructures.RUINS_LAND_WARM);
        }

        if (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandHotAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_LAND_HOT,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.RUINS_LAND_HOT);
        }

        if (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandColdAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_LAND_COLD,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.EXTREME_HILLS, Biome.BiomeCategory.MOUNTAIN) &&
                    event.biome.getPrecipitation() != Biome.Precipitation.SNOW))
        {
            event.addStructure(RSConfiguredStructures.RUINS_LAND_COLD);
        }

        if (RepurposedStructures.RSAllConfig.RSRuinsConfig.ruinsLandIcyAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_LAND_ICY,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.ICY)))
        {
            event.addStructure(RSConfiguredStructures.RUINS_LAND_ICY);
        }
    }
}
