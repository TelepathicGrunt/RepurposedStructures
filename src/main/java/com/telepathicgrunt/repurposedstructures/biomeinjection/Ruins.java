package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSRuinsConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;

public final class Ruins {
    private Ruins() {}

    public static void addRuins(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSRuinsConfig.ruinsNetherAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_NETHER.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER)))
        {
            event.addStructure(RSConfiguredStructures.RUINS_NETHER);
        }

        if (RSRuinsConfig.ruinsLandWarmAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_LAND_WARM.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.TAIGA, Biome.BiomeCategory.SWAMP) &&
                    !BiomeSelection.hasNameTemp(event, "snow", "ice", "frozen") &&
                    event.biome.getBaseTemperature() >= 0.25f))
        {
            event.addStructure(RSConfiguredStructures.RUINS_LAND_WARM);
        }

        if (RSRuinsConfig.ruinsLandHotAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.RUINS_LAND_HOT.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.DESERT)))
        {
            event.addStructure(RSConfiguredStructures.RUINS_LAND_HOT);
        }
    }
}
