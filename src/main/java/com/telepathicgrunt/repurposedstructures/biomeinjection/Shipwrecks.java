package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Shipwrecks {
    private Shipwrecks() {}

    public static void addShipwrecks() {

        BiomeInjection.addStructure(RSConfiguredStructures.SHIPWRECK_END, (event) ->
            (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.endShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_END,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiome(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        );

        //Nether based Shipwrecks
        BiomeInjection.addStructure(RSConfiguredStructures.SHIPWRECK_CRIMSON, (event) ->
            (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.crimsonShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_CRIMSON,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "crimson", "red_")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.SHIPWRECK_WARPED, (event) ->
            (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.warpedShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_WARPED,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasName(event, "warped", "blue")))
        );

        BiomeInjection.addStructure(RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS, (event) ->
            (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.netherBricksShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowed(event, RSStructures.SHIPWRECK_NETHER_BRICKS,
                    () -> BiomeSelection.haveCategories(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasName(event, "crimson", "red_", "warped", "blue")))
        );
    }
}
