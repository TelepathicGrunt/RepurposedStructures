package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Igloos {
    private Igloos() {}

    public static void addIgloos() {

        BiomeInjection.addStructure(RSConfiguredStructures.IGLOO_GRASSY, (event) ->
            (RepurposedStructures.RSAllConfig.RSIgloosConfig.grassyIglooAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.IGLOO_GRASSY,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.PLAINS) ||
            BiomeSelection.isBiome(event, Biomes.MEADOW)))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.IGLOO_STONE, (event) ->
            (RepurposedStructures.RSAllConfig.RSIgloosConfig.stoneIglooAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.IGLOO_STONE,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.TAIGA) &&
            BiomeSelection.hasName(event, "giant", "redwood", "old_growth")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.IGLOO_MUSHROOM, (event) ->
            (RepurposedStructures.RSAllConfig.RSIgloosConfig.mushroomIglooAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.IGLOO_MUSHROOM,
            () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.MUSHROOM)))
        );
    }
}
