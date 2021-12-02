package com.telepathicgrunt.repurposedstructures.biomeinjection;

import com.telepathicgrunt.repurposedstructures.configs.RSShipwrecksConfig;
import com.telepathicgrunt.repurposedstructures.modinit.RSConfiguredStructures;
import com.telepathicgrunt.repurposedstructures.modinit.RSStructures;
import com.telepathicgrunt.repurposedstructures.utils.BiomeSelection;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public final class Shipwrecks {
    private Shipwrecks() {}

    public static void addShipwrecks(TemporaryBiomeInjection.BiomeInjectionHelper event) {

        if (RSShipwrecksConfig.shipwreckEndAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_END.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.THEEND) &&
                    !BiomeSelection.isBiomeTemp(event, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_BARRENS)))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_END);
        }

        //Nether based Shipwrecks
        if (RSShipwrecksConfig.shipwreckCrimsonAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_CRIMSON.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "crimson", "red_")))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_CRIMSON);
        }

        if (RSShipwrecksConfig.shipwreckWarpedAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_WARPED.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    BiomeSelection.hasNameTemp(event, "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_WARPED);
        }

        if (RSShipwrecksConfig.shipwreckNetherBricksAverageChunkDistance.get() != 1001 &&
            BiomeSelection.isBiomeAllowedTemp(event, RSStructures.SHIPWRECK_NETHER_BRICKS.get(),
                    () -> BiomeSelection.haveCategoriesTemp(event, Biome.BiomeCategory.NETHER) &&
                    !BiomeSelection.hasNameTemp(event, "crimson", "red_", "warped", "blue")))
        {
            event.addStructure(RSConfiguredStructures.SHIPWRECK_NETHER_BRICKS);
        }
    }
}
