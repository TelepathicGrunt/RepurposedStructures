package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Shipwrecks {
    private Shipwrecks() {}

    public static void addShipwrecks(BiomeInjection.BiomeInjectionHelper event) {

        if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.endShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_END,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiomeTemp(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_END);
        }

        //Nether based Shipwrecks
        if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.crimsonShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_CRIMSON,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_CRIMSON);
        }

        if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.warpedShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_WARPED,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_WARPED);
        }

        if (RepurposedStructures.RSAllConfig.RSShipwrecksConfig.netherBricksShipwreckAverageChunkDistance != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_NETHER_BRICKS,
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "crimson", "red_", "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS);
        }
    }
}
