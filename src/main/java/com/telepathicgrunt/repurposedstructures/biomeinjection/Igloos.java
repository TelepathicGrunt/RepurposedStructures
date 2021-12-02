package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSIgloosConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Igloos {
    private Igloos() {}

    public static void addIgloos(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSIgloosConfig.grassyIglooAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.IGLOO_GRASSY.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.PLAINS) ||
                    BiomeSelection.isBiomeTemp(event, Biomes.MEADOW)))
        {
            event.addStructure(RSConfiguredStructures.IGLOO_GRASSY);
        }

        if (RSIgloosConfig.stoneIglooAverageChunkDistance.get() != 1001 &&
                BiomeSelection.isBiomeAllowedTemp(event, RSStructures.IGLOO_STONE.get(),
                        () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.TAIGA) &&
                        BiomeSelection.hasNameTemp(event, "giant", "redwood", "old_growth")))
        {
            event.addStructure(RSConfiguredStructures.IGLOO_STONE);
        }
    }
}
